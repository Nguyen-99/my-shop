package com.nuce.service;

import com.nuce.dao.BillDao;
import com.nuce.dao_impl.BillDaoImpl;
import com.nuce.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements BillDao {
    @Autowired
    private BillDaoImpl billDao;
    @Override
    public Bill getById(int id) {
        return billDao.getById(id) ;
    }

    @Override
    public List<Bill> getByCustomer(int customerId) {
        return billDao.getByCustomer(customerId);
    }

    @Override
    public Bill insert(Bill bill) {
        return billDao.insert(bill);
    }
}
