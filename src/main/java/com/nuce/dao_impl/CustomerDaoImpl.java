package com.nuce.dao_impl;

import com.nuce.dao.CustomerDao;
import com.nuce.model.Customer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer login(String username, String password) {
        Customer customer=null;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from customer where username=? and password=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                customer=new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAvatar(rs.getString("avatar"));
                customer.setUsername(username);
                customer.setPassword(password);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean register(Customer customer) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into customer(name,address,phone,email,username,password) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getAddress());
            ps.setString(3,customer.getPhone());
            ps.setString(4,customer.getEmail());
            ps.setString(5,customer.getUsername());
            ps.setString(6,customer.getPassword());
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
    public List<Customer> getAll() {
        List<Customer> customers=new ArrayList<>();
        Connection con= JDBCConnection.getJDBCConnection();
        String sql="select * from customer";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Customer customer=new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAvatar(rs.getString("avatar"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customers.add(customer);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getById(int id) {
        Customer customer=null;
        Connection con= JDBCConnection.getJDBCConnection();
        String sql="select * from customer where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                customer=new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAvatar(rs.getString("avatar"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
    @Override
    public boolean insert(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="update customer set name=?,phone=?,email=?,address=?,avatar=? where id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getPhone());
            ps.setString(3,customer.getEmail());
            ps.setString(4,customer.getAddress());
            ps.setString(5,customer.getAvatar());
            ps.setInt(6,customer.getId());
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
        return false;
    }
}
