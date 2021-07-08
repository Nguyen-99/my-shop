package com.nuce.dao;

import com.nuce.model.District;
import com.nuce.model.Province;
import com.nuce.model.Ward;

import java.util.List;

public interface AddressDao {
    List<Province> getAllProvince();
    List<District> getDistrictByProvince(String provinceId);
    List<Ward> getWardByDistrict(String districtId);
}
