package com.nuce.dao;

import java.util.List;

public interface BaseDao<T> {
    List<T> getAll();
    T getById(int id);
    boolean insert(T t);
    boolean update(T t);
    boolean delete(int id);
}
