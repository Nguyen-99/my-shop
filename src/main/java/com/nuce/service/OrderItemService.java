package com.nuce.service;

import com.nuce.dao.OrderItemDao;
import com.nuce.dao_impl.OrderItemDaoImpl;
import com.nuce.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements OrderItemDao {
    @Autowired
    private OrderItemDaoImpl orderItemDao;
    @Override
    public OrderItem getById(int id) {
        return orderItemDao.getById(id);
    }

    @Override
    public List<OrderItem> getByBill(int billId) {
        return orderItemDao.getByBill(billId);
    }

    @Override
    public boolean insert(OrderItem orderItem) {
        return orderItemDao.insert(orderItem);
    }
}
