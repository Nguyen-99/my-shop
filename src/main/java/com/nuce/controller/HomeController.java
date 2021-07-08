package com.nuce.controller;

import com.nuce.model.Customer;
import com.nuce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/","/home"})
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private DetailProductService detailProductService;
    @Autowired
    private ItemService itemService;
    @GetMapping
    public String home(HttpSession session,ModelMap modelMap){
        Customer customer= (Customer) session.getAttribute("customer");
        modelMap.addAttribute("customer",customer);
        return "user_view/home";
    }
    @GetMapping("/danhmuc/nam")
    public String men(ModelMap modelMap){
        modelMap.addAttribute("list_category",categoryService.getCategoryByGender(true));
        modelMap.addAttribute("list_product",productService.getProductByGender(true));
        return "user_view/men";
    }
    @GetMapping("/danhmuc/nam/{id}")
    public String productByCategory(ModelMap modelMap, @PathVariable("id") int id){
        modelMap.addAttribute("list_category",categoryService.getCategoryByGender(true));
        modelMap.addAttribute("list_product",productService.getProductByCategory(id));
        return "user_view/product1";
    }
    @GetMapping("/danhmuc/nu")
    public String women(ModelMap modelMap){
        modelMap.addAttribute("list_category",categoryService.getCategoryByGender(false));
        modelMap.addAttribute("list_product",productService.getProductByGender(false));
        return "user_view/women";
    }
    @GetMapping("/danhmuc/nu/{id}")
    public String productByCategory2(ModelMap modelMap, @PathVariable("id") int id){
        modelMap.addAttribute("list_category",categoryService.getCategoryByGender(false));
        modelMap.addAttribute("list_product",productService.getProductByCategory(id));
        return "user_view/product2";
    }
    @GetMapping("/sanpham/{id}")
    public String detail(ModelMap modelMap, @PathVariable("id") int id){
        modelMap.addAttribute("product",productService.getById(id));
        modelMap.addAttribute("images",imageService.getImageByProductId(id));
        modelMap.addAttribute("sizes",detailProductService.getListSize(id));
        modelMap.addAttribute("colors",detailProductService.getListColor(id));
        return "user_view/detail";
    }
}
