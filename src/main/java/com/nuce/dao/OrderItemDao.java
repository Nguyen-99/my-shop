package com.nuce.dao;

import com.nuce.model.OrderItem;

import java.util.List;

public interface OrderItemDao {
    OrderItem getById(int id);
    List<OrderItem> getByBill(int billId);
    boolean insert(OrderItem orderItem);
}
