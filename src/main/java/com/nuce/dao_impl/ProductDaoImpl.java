package com.nuce.dao_impl;

import com.nuce.dao.ProductDao;
import com.nuce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
                product.setActive(rs.getBoolean("active"));
                product.setPriority(rs.getInt("priority"));
                product.setCreateTime(rs.getTimestamp("create_time"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
                products.add(product);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public List<Product> getByPage(boolean gender,int n, int total) {
        List<Product> products=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product inner join category on product.category_id=category.id where category.gender= ? limit ?,?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1,gender);
            ps.setInt(2,n-1);
            ps.setInt(3,total);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setActive(rs.getBoolean("active"));
                product.setPriority(rs.getInt("priority"));
                product.setCreateTime(rs.getTimestamp("create_time"));
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
        Product product=null;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                product=new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setActive(rs.getBoolean("active"));
                product.setPriority(rs.getInt("priority"));
                product.setCreateTime(rs.getTimestamp("create_time"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
            }
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
        String sql="insert into product(name,price,image,description,active,priority,create_time,category_id) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setString(3,product.getImage());
            ps.setString(4,product.getDescription());
            ps.setBoolean(5,product.isActive());
            ps.setInt(6,product.getPriority());
            long time=System.currentTimeMillis();
            Timestamp timestamp=new Timestamp(time);
            ps.setTimestamp(7,timestamp);
            ps.setInt(8,product.getCategory().getId());
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
        String sql="update product set name=?,price=?,image=?,description=?,active=?,priority=?,category_id=? where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3,product.getImage());
            ps.setString(4,product.getDescription());
            ps.setBoolean(5,product.isActive());
            ps.setInt(6,product.getPriority());
            ps.setInt(7,product.getCategory().getId());
            ps.setInt(8,product.getId());
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
                product.setActive(rs.getBoolean("active"));
                product.setPriority(rs.getInt("priority"));
                product.setCreateTime(rs.getTimestamp("create_time"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
                products.add(product);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> search(boolean gender, String query) {
        List<Product> products=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product inner join category on product.category_id=category.id where " +
                "(category.gender=? and (lower(product.name) like lower(?) or lower(category.name) like lower(?)))";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1,gender);
            ps.setString(2,"%"+query+"%");
            ps.setString(3,"%"+query+"%");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setActive(rs.getBoolean("active"));
                product.setPriority(rs.getInt("priority"));
                product.setCreateTime(rs.getTimestamp("create_time"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
                products.add(product);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> search(boolean gender,int categoryId,String query) {
        List<Product> products=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from product inner join category on product.category_id=category.id where" +
                " (category.gender=? and product.category_id=? and (lower(product.name) like lower(?) or lower(category.name) like lower(?)))";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1,gender);
            ps.setInt(2,categoryId);
            ps.setString(3,"%"+query+"%");
            ps.setString(4,"%"+query+"%");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setActive(rs.getBoolean("active"));
                product.setPriority(rs.getInt("priority"));
                product.setCreateTime(rs.getTimestamp("create_time"));
                product.setCategory(categoryDao.getById(rs.getInt("category_id")));
                products.add(product);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
