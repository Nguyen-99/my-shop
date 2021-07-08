package com.nuce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String adminHome(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "admin_view/home";
        } else {
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/login")
    public String loginAdmin() {
        return "admin_view/login_admin";
    }

    @PostMapping("/check")
    public String checkLogin(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("username", username);
            return "redirect:/admin";
        } else {
            request.setAttribute("check", "Sai tên đăng nhập hoặc mật khẩu");
            return "admin_view/login_admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/admin/login";
    }

}
