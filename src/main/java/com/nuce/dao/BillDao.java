package com.nuce.dao;

import com.nuce.model.Bill;

import java.util.Date;
import java.util.List;

public interface BillDao {
    Bill getById(int id);
    List<Bill> getByCustomer(int customerId);
    Bill insert(Bill bill);
    List<Bill> getByDate(Date date);
    List<Bill> getAll();
    List<Bill> searchByDate(Date date1,Date date2);
}
