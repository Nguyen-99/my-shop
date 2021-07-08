package com.nuce.dao_impl;

import com.nuce.dao.ImageDao;
import com.nuce.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private ProductDaoImpl productDao;
    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public Image getById(int id) {
        Image image=new Image();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from image where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            image.setId(rs.getInt("id"));
            image.setImage(rs.getString("image"));
            image.setProduct(productDao.getById(rs.getInt("product_id")));
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return image;
    }

    @Override
    public boolean insert(Image image) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into image(image,product_id) values(?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,image.getImage());
            ps.setInt(2,image.getProduct().getId());
            int rs=ps.executeUpdate();
            if(rs>0){
                check=true;
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean update(Image image) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="update image set image=? where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,image.getImage());
            ps.setInt(2,image.getId());
            int rs=ps.executeUpdate();
            if(rs>0){
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
        String sql="delete from image where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            int rs=ps.executeUpdate();
            if(rs>0){
                check=true;
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Image> getImageByProductId(int id) {
        List<Image> images=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from image where product_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Image image=new Image();
                image.setId(rs.getInt("id"));
                image.setImage(rs.getString("image"));
                image.setProduct(productDao.getById(id));
                images.add(image);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return images;
    }
}
