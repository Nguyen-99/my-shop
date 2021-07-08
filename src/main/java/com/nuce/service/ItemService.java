package com.nuce.service;

import com.nuce.dao.ItemDao;
import com.nuce.dao_impl.ItemDaoImpl;
import com.nuce.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements ItemDao {
    @Autowired
    ItemDaoImpl itemDao;

    @Override
    public Item getById(int id) {
        return itemDao.getById(id);
    }

    @Override
    public List<Item> getByCustomerId(int id) {
        return itemDao.getByCustomerId(id);
    }

    @Override
    public boolean insert(Item item) {
        for (Item item1:getByCustomerId(item.getCustomer().getId())) {
            if(item1.getDetailProduct().getId()==item.getDetailProduct().getId()){
                item1.setNumber(item.getNumber()+item1.getNumber());
                return itemDao.update(item1);
            }
        }
        return itemDao.insert(item);
    }

    @Override
    public boolean update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public boolean delete(int id) {
        return itemDao.delete(id);
    }
    public double totalMoney(int customerId){
        double m=0;
        for (Item item:getByCustomerId(customerId)) {
            m+=item.getDetailProduct().getProduct().getPrice()*item.getNumber();
        }
        return m;
    }
}
