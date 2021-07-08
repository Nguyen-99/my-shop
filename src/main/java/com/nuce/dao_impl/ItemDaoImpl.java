package com.nuce.dao_impl;

import com.nuce.dao.ItemDao;
import com.nuce.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{
    @Autowired
    DetailProductDaoImpl detailProductDao;
    @Autowired
    CustomerDaoImpl customerDao;

    @Override
    public Item getById(int id) {
        Item item=null;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from item where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                item=new Item();
                item.setId(id);
                item.setDetailProduct(detailProductDao.getById(rs.getInt("dp_id")));
                item.setNumber(rs.getInt("number"));
                item.setCustomer(customerDao.getById(rs.getInt("customer_id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return item;
    }

    @Override
    public List<Item> getByCustomerId(int id) {
        List<Item> items=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from item where customer_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Item item=new Item();
                item.setId(rs.getInt("id"));
                item.setDetailProduct(detailProductDao.getById(rs.getInt("dp_id")));
                item.setNumber(rs.getInt("number"));
                item.setCustomer(customerDao.getById(id));
                items.add(item);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return items;
    }

    @Override
    public boolean insert(Item item) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into item(dp_id,number,customer_id) values(?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,item.getDetailProduct().getId());
            ps.setInt(2,item.getNumber());
            ps.setInt(3,item.getCustomer().getId());
            int rs=ps.executeUpdate();
            if(rs>0){
                check=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean update(Item item) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="update item set number=? where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,item.getNumber());
            ps.setInt(2,item.getId());
            int rs=ps.executeUpdate();
            if(rs>0){
                check=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="delete from item where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            int rs=ps.executeUpdate();
            if(rs>0){
                check=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }
}
