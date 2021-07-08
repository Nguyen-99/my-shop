package com.nuce.dao;

import com.nuce.model.Customer;

public interface CustomerDao extends BaseDao<Customer>{
    Customer login(String username,String password);
    boolean register(Customer customer);
}
