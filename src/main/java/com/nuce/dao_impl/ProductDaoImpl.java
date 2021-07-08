package com.nuce.dao_impl;

import com.nuce.dao.ProductDao;
import com.nuce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private CategoryDaoImpl categoryDao;
    @Override
    public List<Product> getAll() {
        List<Product> products=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product";
        try {
            Statement statement= con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
                products.add(product);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public List<Product> getByPage(int pageId, int total) {
        List<Product> products=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product limit ?,?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,pageId-1);
            ps.setInt(2,total);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        Product product=new Product();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            product.setId(id);
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setImage(rs.getString("image"));
            product.setDescription(rs.getString("description"));
            product.setCategory(categoryDao.getById(rs.getInt("category_id")));
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean insert(Product product) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into product(name,price,image,description,category_id) values (?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setString(3,product.getImage());
            ps.setString(4,product.getDescription());
            ps.setInt(5,product.getCategory().getId());
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
    public boolean update(Product product) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="update product set name=?,price=?,image=?,description=?,category_id=? where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3,product.getImage());
            ps.setString(4,product.getDescription());
            ps.setInt(5,product.getCategory().getId());
            ps.setInt(6,product.getId());
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
        String sql="delete from product where id=?";
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
    public List<Product> getProductByCategory(int id) {
        List<Product> products=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product where category_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
