package com.nuce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuce.model.*;
import com.nuce.service.AddressService;
import com.nuce.service.CustomerService;
import com.nuce.service.DetailProductService;
import com.nuce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
    @Autowired
    private DetailProductService detailProductService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;
    @RequestMapping("/get-detail")
    @ResponseBody
    public String getNumber(HttpServletRequest request){
        int productId= Integer.parseInt(request.getParameter("productId"));
        String color=request.getParameter("color");
        String size=request.getParameter("size");
        if(size!=null&&color!=null){
            DetailProduct detailProduct=detailProductService.getDetail(productId,size,color);

            ObjectMapper mapper=new ObjectMapper();
            String ajaxResponse="";
            try {
                ajaxResponse=mapper.writeValueAsString(detailProduct);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ajaxResponse;
        }
        return null;
    }
    @RequestMapping("/add-cart")
    @ResponseBody
    public String addCart(HttpServletRequest request){
        int productId= Integer.parseInt(request.getParameter("productId"));
        String color=request.getParameter("color");
        String size=request.getParameter("size");
        DetailProduct detailProduct=detailProductService.getDetail(productId,size,color);
        int customerId= Integer.parseInt(request.getParameter("customerId"));
        int n= Integer.parseInt(request.getParameter("n"));
        Item item=new Item();
        item.setDetailProduct(detailProduct);
        item.setNumber(n);
        item.setCustomer(customerService.getById(customerId));
        boolean check=itemService.insert(item);
        if(check){
            HttpSession session=request.getSession();
            session.setAttribute("num_item",itemService.getByCustomerId(customerId).size());
            return "true";
        }else {
            return "false";
        }
    }
    @RequestMapping("/buy-now")
    @ResponseBody
    public String buyNow(HttpServletRequest request){
        int productId= Integer.parseInt(request.getParameter("productId"));
        String color=request.getParameter("color");
        String size=request.getParameter("size");
        DetailProduct detailProduct=detailProductService.getDetail(productId,size,color);
        int customerId= Integer.parseInt(request.getParameter("customerId"));
        int n= Integer.parseInt(request.getParameter("n"));
        Item item=new Item();
        item.setDetailProduct(detailProduct);
        item.setNumber(n);
        item.setCustomer(customerService.getById(customerId));
        List<Item> items=new ArrayList<>();
        items.add(item);
        HttpSession session=request.getSession();
        session.setAttribute("items",items);
        return "ok";
    }
    @RequestMapping("/update-cart")
    @ResponseBody
    public String updateteCart(HttpServletRequest request){
        int itemId= Integer.parseInt(request.getParameter("itemId"));
        int number= Integer.parseInt(request.getParameter("number"));
        Item item=itemService.getById(itemId);
        item.setNumber(number);
        boolean check=itemService.update(item);
        if(check){
            return "true";
        }else {
            return "false";
        }
    }
    @RequestMapping("/get-list-district")
    @ResponseBody
    public String getListDistrict(HttpServletRequest request){
        String provinceId= request.getParameter("provinceId");
        List<District> districts=addressService.getDistrictByProvince(provinceId);
        ObjectMapper mapper=new ObjectMapper();
        String ajaxResponse="";
        try {
            ajaxResponse=mapper.writeValueAsString(districts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }
    @RequestMapping("/get-list-ward")
    @ResponseBody
    public String getListWard(HttpServletRequest request){
        String districtId= request.getParameter("districtId");
        List<Ward> wards=addressService.getWardByDistrict(districtId);
        ObjectMapper mapper=new ObjectMapper();
        String ajaxResponse="";
        try {
            ajaxResponse=mapper.writeValueAsString(wards);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }
    @GetMapping("/getSelectedItem")
    @ResponseBody
    public String getSelectedItem(HttpServletRequest request, @RequestParam("listChecked[]") List<Integer> a){
        List<Item> items=new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            Item item=itemService.getById(a.get(i));
            items.add(item);
        }
        HttpSession session=request.getSession();
        session.setAttribute("items",items);
        return "";
    }
}
