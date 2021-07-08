package com.nuce.dao;

import com.nuce.model.DetailProduct;

import java.util.List;

public interface DetailProductDao{
    DetailProduct getById(int id);
    List<DetailProduct> getDetailByProductId(int id);
    boolean insertOrUpdate(DetailProduct detailProduct);
    boolean update(DetailProduct detailProduct);
    boolean delete(int id);
}
