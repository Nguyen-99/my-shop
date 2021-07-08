package com.nuce.dao_impl;

import com.nuce.dao.OrderItemDao;
import com.nuce.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private DetailProductDaoImpl detailProductDao;
    @Autowired
    private BillDaoImpl billDao;
    @Override
    public OrderItem getById(int id) {
        OrderItem orderItem=new OrderItem();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from order_item where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery(sql);
            rs.next();
            orderItem.setId(id);
            orderItem.setDetailProduct(detailProductDao.getById(rs.getInt("dp_id")));
            orderItem.setNumber(rs.getInt("number"));
            orderItem.setBill(billDao.getById(rs.getInt("bill_id")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> getByBill(int billId) {
        List<OrderItem> orderItems=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from order_item where bill_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,billId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                OrderItem orderItem=new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setBill(billDao.getById(billId));
                orderItem.setNumber(rs.getInt("number"));
                orderItem.setDetailProduct(detailProductDao.getById(rs.getInt("dp_id")));
                orderItems.add(orderItem);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderItems;
    }

    @Override
    public boolean insert(OrderItem orderItem) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into order_item(dp_id,number,bill_id) values (?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,orderItem.getDetailProduct().getId());
            ps.setInt(2,orderItem.getNumber());
            ps.setInt(3,orderItem.getBill().getId());
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
}
