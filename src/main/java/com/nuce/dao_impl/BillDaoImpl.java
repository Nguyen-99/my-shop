package com.nuce.dao_impl;

import com.nuce.dao.BillDao;
import com.nuce.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BillDaoImpl implements BillDao {
    @Autowired
    private CustomerDaoImpl customerDao;
    @Override
    public Bill getById(int id) {
        Bill bill=new Bill();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from bill where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            bill.setId(id);
            bill.setCustomer(customerDao.getById(rs.getInt("customer_id")));
            bill.setCreateDate(rs.getTimestamp("create_date"));
            bill.setDeliveryAddress(rs.getString("delivery_address"));
            bill.setStatus(rs.getBoolean("status"));
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bill;
    }

    @Override
    public List<Bill> getByCustomer(int customerId) {
        List<Bill> bills=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from bill where customer_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,customerId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Bill bill=new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCustomer(customerDao.getById(rs.getInt("customer_id")));
                bill.setCreateDate(rs.getTimestamp("create_time"));
                bill.setDeliveryAddress(rs.getString("delivery_address"));
                bill.setStatus(rs.getBoolean("status"));
                bills.add(bill);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bills;
    }

    @Override
    public Bill insert(Bill bill) {
        Bill newBill=new Bill();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into bill(customer_id,create_date,delivery_address,status) values (?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,bill.getCustomer().getId());
            long time=System.currentTimeMillis();
            Timestamp timestamp=new Timestamp(time);
            ps.setTimestamp(2,timestamp);
            ps.setString(3,bill.getDeliveryAddress());
            ps.setBoolean(4,bill.isStatus());
            int rs=ps.executeUpdate();
            if(rs>0){
                ResultSet resultSet=ps.getGeneratedKeys();
                if(resultSet.next()){
                    int id=resultSet.getInt(1);
                    newBill=getById(id);
                }
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newBill;
    }
}
