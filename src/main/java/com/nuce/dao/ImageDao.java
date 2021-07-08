package com.nuce.dao;

import com.nuce.model.Image;

import java.util.List;

public interface ImageDao extends BaseDao<Image>{
    List<Image> getImageByProductId(int id);
}
