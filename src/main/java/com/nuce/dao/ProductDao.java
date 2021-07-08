package com.nuce.dao;

import com.nuce.model.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {
    List<Product> getProductByCategory(int id);
}