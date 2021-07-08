package com.nuce.dao_impl;

import com.nuce.dao.DetailProductDao;
import com.nuce.model.DetailProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DetailProductDaoImpl implements DetailProductDao {
    @Autowired
    private ProductDaoImpl productDao;

    @Override
    public DetailProduct getById(int id) {
        DetailProduct detailProduct = new DetailProduct();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "select * from detail_product where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            detailProduct.setId(id);
            detailProduct.setProduct(productDao.getById(rs.getInt("product_id")));
            detailProduct.setSize((rs.getString("size")));
            detailProduct.setColor(rs.getString("color"));
            detailProduct.setNumber(rs.getInt("number"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return detailProduct;
    }

    @Override
    public List<DetailProduct> getDetailByProductId(int id) {
        List<DetailProduct> detailProducts = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "select * from detail_product where product_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetailProduct detailProduct = new DetailProduct();
                detailProduct.setId(rs.getInt("id"));
                detailProduct.setProduct(productDao.getById(rs.getInt("product_id")));
                detailProduct.setSize((rs.getString("size")));
                detailProduct.setColor(rs.getString("color"));
                detailProduct.setNumber(rs.getInt("number"));
                detailProducts.add(detailProduct);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return detailProducts;
    }

    public int checkDup(DetailProduct detailProduct) {
        int id = 0;
        for (DetailProduct detail : getDetailByProductId(detailProduct.getProduct().getId())) {
            if (detail.getSize().equals(detailProduct.getSize().toUpperCase()) && detail.getColor().equals(detailProduct.getColor().toLowerCase())) {
                id = detail.getId();
                break;
            }
        }
        return id;
    }

    @Override
    public boolean insertOrUpdate(DetailProduct detailProduct) {
        Connection con = JDBCConnection.getJDBCConnection();
        boolean check = false;
        if (checkDup(detailProduct) > 0) {
            String sql = "update detail_product set number=? where id=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, getById(checkDup(detailProduct)).getNumber() + detailProduct.getNumber());
                ps.setInt(2, checkDup(detailProduct));
                int rs = ps.executeUpdate();
                if (rs > 0) {
                    check = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            String sql = "insert into detail_product(product_id,size,color,number) value (?,?,?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, detailProduct.getProduct().getId());
                ps.setString(2, detailProduct.getSize().toUpperCase());
                ps.setString(3, detailProduct.getColor().toLowerCase());
                ps.setInt(4, detailProduct.getNumber());
                int rs = ps.executeUpdate();
                if (rs > 0) {
                    check = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return check;
    }

    @Override
    public boolean update(DetailProduct detailProduct) {
        Connection con = JDBCConnection.getJDBCConnection();
        boolean check = false;
        String sql = "update detail_product set size=?,color=?,number=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, detailProduct.getSize());
            ps.setString(2, detailProduct.getColor());
            ps.setInt(3, detailProduct.getNumber());
            ps.setInt(4,detailProduct.getId());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                check = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        Connection con = JDBCConnection.getJDBCConnection();
        boolean check = false;
        String sql = "delete from detail_product where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                check = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }
    public List<String> getListSize(int productId){
        Connection con=JDBCConnection.getJDBCConnection();
        List<String> sizes=new ArrayList<>();
        String sql="select distinct size from detail_product where product_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,productId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                String size=rs.getString("size");
                sizes.add(size);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sizes;
    }
    public List<String> getListColor(int productId){
        Connection con=JDBCConnection.getJDBCConnection();
        List<String> colors=new ArrayList<>();
        String sql="select distinct color from detail_product where product_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,productId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                String color=rs.getString("color");
                colors.add(color);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return colors;
    }
}
