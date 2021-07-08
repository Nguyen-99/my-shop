package com.nuce.service;

import com.nuce.dao.AddressDao;
import com.nuce.dao_impl.AddressDaoImpl;
import com.nuce.model.District;
import com.nuce.model.Province;
import com.nuce.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressDao {
    @Autowired
    private AddressDaoImpl addressDao;
    @Override
    public List<Province> getAllProvince() {
        return addressDao.getAllProvince();
    }

    @Override
    public List<District> getDistrictByProvince(String provinceId) {
        return addressDao.getDistrictByProvince(provinceId);
    }

    @Override
    public List<Ward> getWardByDistrict(String districtId) {
        return addressDao.getWardByDistrict(districtId);
    }
}
