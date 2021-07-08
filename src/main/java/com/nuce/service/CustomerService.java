package com.nuce.service;

import com.nuce.dao.CustomerDao;
import com.nuce.dao_impl.CustomerDaoImpl;
import com.nuce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CustomerDao {
    @Autowired
    CustomerDaoImpl customerDao;
    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    @Override
    public boolean insert(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Customer login(String username, String password) {
        return customerDao.login(username,password);
    }

    @Override
    public boolean register(Customer customer) {
        return customerDao.register(customer);
    }

    public boolean checkDuplicate(Customer customer){
        boolean check=false;
        for (Customer customer1:getAll()) {
            if(customer.getUsername().equals(customer1.getUsername())){
                check=true;
                break;
            }
        }
        return check;
    }
}
