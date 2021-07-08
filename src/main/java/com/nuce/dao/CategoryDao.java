package com.nuce.dao;

import com.nuce.model.Category;

import java.util.List;

public interface CategoryDao extends BaseDao<Category>{
    List<Category> getCategoryByGender(boolean gender);
}
