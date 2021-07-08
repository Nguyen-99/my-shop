package com.nuce.controller;

import com.nuce.model.Customer;
import com.nuce.service.CustomerService;
import com.nuce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ItemService itemService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "user_view/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = customerService.login(username, password);
        if (customer != null) {
            HttpSession session=request.getSession();
            session.setAttribute("customer",customer);
            session.setAttribute("num_item",itemService.getByCustomerId(customer.getId()).size());
            return "redirect:/";
        } else {
            request.setAttribute("msg", "Sai tên đăng nhập hoặc mật khẩu");
            return "user_view/login";
        }
    }
    @RequestMapping(value = "/logout-user",method = RequestMethod.GET )
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(ModelMap modelMap) {
        modelMap.addAttribute("new_customer", new Customer());
        System.out.println(customerService.getAll());
        return "user_view/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("new_customer") Customer customer, HttpServletRequest request) {
        if (customerService.checkDuplicate(customer)) {
            request.setAttribute("msg", "Đã tồn tại tên đăng nhập");
        } else {
            boolean check = customerService.register(customer);
            if (check) {
                request.setAttribute("msg", "Đăng ký thành công");
            } else {
                request.setAttribute("msg", "Đăng ký thất bại");
            }
        }
        return "user_view/register";
    }

}
