package com.nuce.service;

import com.nuce.dao.DetailProductDao;
import com.nuce.dao_impl.DetailProductDaoImpl;
import com.nuce.model.DetailProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailProductService implements DetailProductDao {
    @Autowired
    private DetailProductDaoImpl detailProductDao;

    @Override
    public DetailProduct getById(int id) {
        return detailProductDao.getById(id);
    }

    @Override
    public List<DetailProduct> getDetailByProductId(int id) {
        return detailProductDao.getDetailByProductId(id);
    }

    @Override
    public boolean insertOrUpdate(DetailProduct detailProduct) {
        return detailProductDao.insertOrUpdate(detailProduct);
    }

    @Override
    public boolean update(DetailProduct detailProduct) {
        return detailProductDao.update(detailProduct);
    }

    @Override
    public boolean delete(int id) {
        return detailProductDao.delete(id);
    }

    public List<String> getListSize(int productId){
        return detailProductDao.getListSize(productId);
    }
    public List<String> getListColor(int productId){
        return detailProductDao.getListColor(productId);
    }
    public DetailProduct getDetail(int productId,String size,String color){
        DetailProduct detail=new DetailProduct();
        for (DetailProduct detailProduct:getDetailByProductId(productId)) {
            if(detailProduct.getSize().equals(size)&&detailProduct.getColor().equals(color)){
                detail=detailProduct;
                break;
            }
        }
        return detail;
    }
}
