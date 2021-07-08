package com.nuce.dao;

import com.nuce.model.Bill;

import java.util.List;

public interface BillDao {
    Bill getById(int id);
    List<Bill> getByCustomer(int customerId);
    Bill insert(Bill bill);
}
