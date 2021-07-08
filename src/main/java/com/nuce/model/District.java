package com.nuce.model;

import com.nuce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class District {
    private String id;
    private String name;
    private String type;
    private String provinceId;

    public District() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
