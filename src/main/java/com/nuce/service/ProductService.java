package com.nuce.service;

import com.nuce.dao.ProductDao;
import com.nuce.dao_impl.ProductDaoImpl;
import com.nuce.model.Category;
import com.nuce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService implements ProductDao {
    @Autowired
    private ProductDaoImpl productDao;
    @Autowired
    private CategoryService categoryService;
    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    public List<Product> getByPage(int pageId, int total) {
        return productDao.getByPage(pageId,total);
    }

    @Override
    public Product getById(int id) {
        return productDao.getById(id);
    }

    @Override
    public boolean insert(Product product) {
        return productDao.insert(product);
    }

    @Override
    public boolean update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(int id) {
        return productDao.delete(id);
    }

    @Override
    public List<Product> getProductByCategory(int id) {
        return productDao.getProductByCategory(id);
    }
    public List<Product> getProductByCategoryUserView(int id) {
        List<Product> products=new ArrayList<>();
        List<Product> productList=getProductByCategory(id);
        for (Product product:productList) {
            if(product.isActive()){
                products.add(product);
            }
        }
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPriority()-o2.getPriority();
            }
        });
        return products;
    }

    public List<Product> getProductByGender(boolean gender){
        List<Product> products=new ArrayList<>();
        List<Category> categories=categoryService.getCategoryByGender(gender);
        for (Category category:categories) {
            products.addAll(getProductByCategory(category.getId()));
        }
        return products;
    }
    public List<Product> getProductByGenderUserView(boolean gender){
        List<Product> products=new ArrayList<>();
        List<Product> productList=getProductByGender(gender);
        for (Product product:productList) {
            if(product.isActive()){
                products.add(product);
            }
        }
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPriority()-o2.getPriority();
            }
        });
        return products;
    }

}
