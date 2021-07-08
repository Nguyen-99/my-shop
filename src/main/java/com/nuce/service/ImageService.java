package com.nuce.service;

import com.nuce.dao.ImageDao;
import com.nuce.dao_impl.ImageDaoImpl;
import com.nuce.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements ImageDao {
    @Autowired
    private ImageDaoImpl imageDao;

    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public Image getById(int id) {
        return imageDao.getById(id);
    }

    @Override
    public boolean insert(Image image) {
        return imageDao.insert(image);
    }

    @Override
    public boolean update(Image image) {
        return imageDao.update(image);
    }

    @Override
    public boolean delete(int id) {
        return imageDao.delete(id);
    }

    @Override
    public List<Image> getImageByProductId(int id) {
        return imageDao.getImageByProductId(id);
    }
}
