package com.nuce.dao;

import com.nuce.model.Item;

import java.util.List;

public interface ItemDao {
    Item getById(int id);
    List<Item> getByCustomerId(int id);
    boolean insert(Item item);
    boolean update(Item item);
    boolean delete(int id);
}
