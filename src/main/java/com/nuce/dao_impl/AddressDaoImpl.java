package com.nuce.dao_impl;

import com.nuce.dao.AddressDao;
import com.nuce.model.District;
import com.nuce.model.Province;
import com.nuce.model.Ward;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDao {
    @Override
    public List<Province> getAllProvince() {
        List<Province> provinces=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from province";
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                Province province=new Province();
                province.setId(rs.getString("id"));
                province.setName(rs.getString("name"));
                province.setType(rs.getString("type"));
                provinces.add(province);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return provinces;
    }

    @Override
    public List<District> getDistrictByProvince(String provinceId) {
        List<District> districts=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from district where province_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,provinceId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                District district=new District();
                district.setId(rs.getString("id"));
                district.setName(rs.getString("name"));
                district.setType(rs.getString("type"));
                district.setProvinceId(provinceId);
                districts.add(district);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return districts;
    }

    @Override
    public List<Ward> getWardByDistrict(String districtId) {
       List<Ward> wards=new ArrayList<>();
       Connection con=JDBCConnection.getJDBCConnection();
       String sql="select * from ward where district_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,districtId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Ward ward=new Ward();
                ward.setId(rs.getString("id"));
                ward.setName(rs.getString("name"));
                ward.setType(rs.getString("type"));
                ward.setDistrictId(districtId);
                wards.add(ward);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return wards;
    }
}
