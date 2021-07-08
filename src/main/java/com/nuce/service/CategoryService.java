package com.nuce.service;

import com.nuce.dao.CategoryDao;
import com.nuce.dao_impl.CategoryDaoImpl;
import com.nuce.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryDao {
    @Autowired
    private CategoryDaoImpl categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    public List<Category> getByPage(int pageId, int total) {
        return categoryDao.getByPage(pageId,total);
    }

    @Override
    public Category getById(int id) {
        return categoryDao.getById(id);
    }

    @Override
    public boolean insert(Category category) {
        return categoryDao.insert(category);
    }

    @Override
    public boolean update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public boolean delete(int id) {
        return categoryDao.delete(id);
    }

    @Override
    public List<Category> getCategoryByGender(boolean gender) {
        return categoryDao.getCategoryByGender(gender);
    }
}
