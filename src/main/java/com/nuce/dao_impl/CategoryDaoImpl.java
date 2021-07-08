package com.nuce.dao_impl;

import com.nuce.dao.CategoryDao;
import com.nuce.model.Category;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAll() {
        List<Category> categories=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from category";
        try {
            Statement statement= con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                Category category=new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setGender(rs.getBoolean("gender"));
                category.setActive(rs.getBoolean("active"));
                category.setPriority(rs.getInt("priority"));
                categories.add(category);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }
    public List<Category> getByPage(int pageId, int total) {
        List<Category> categories=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from category limit ?,?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,pageId-1);
            ps.setInt(2,total);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Category category=new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setGender(rs.getBoolean("gender"));
                category.setActive(rs.getBoolean("active"));
                category.setPriority(rs.getInt("priority"));
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category getById(int id) {
        Category category=new Category();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from category where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            category.setId(id);
            category.setName(rs.getString("name"));
            category.setGender(rs.getBoolean("gender"));
            category.setActive(rs.getBoolean("active"));
            category.setPriority(rs.getInt("priority"));
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean insert(Category category) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into category(name,gender,active,priority) values (?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,category.getName());
            ps.setBoolean(2,category.isGender());
            ps.setBoolean(3,category.isActive());
            ps.setInt(4,category.getPriority());
            int rs=ps.executeUpdate();
            if(rs!=0){
                check=true;
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean update(Category category) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="update category set name=?,gender=?,active=?,priority=? where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setBoolean(2, category.isGender());
            ps.setBoolean(3,category.isActive());
            ps.setInt(4,category.getPriority());
            ps.setInt(5,category.getId());
            int rs=ps.executeUpdate();
            if(rs!=0){
                check=true;
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="delete from category where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            int rs=ps.executeUpdate();
            if(rs!=0){
                check=true;
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Category> getCategoryByGender(boolean gender) {
        List<Category> categories=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from category where gender=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1,gender);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Category category=new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setGender(rs.getBoolean("gender"));
                category.setActive(rs.getBoolean("active"));
                category.setPriority(rs.getInt("priority"));
                categories.add(category);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }
}
