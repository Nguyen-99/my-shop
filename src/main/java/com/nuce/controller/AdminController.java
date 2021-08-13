package com.nuce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminHome(HttpSession session, ModelMap modelMap) {
        String username= (String) session.getAttribute("username");
        modelMap.addAttribute("username",username);
        return "admin_view/home";
    }

    @GetMapping("/admin/login")
    public String loginAdmin() {
        return "admin_view/login_admin";
    }

    @PostMapping("/check")
    public String checkLogin(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("username", username);
            return "redirect:/admin";
        }else if(username.equals("nguyen") && password.equals("1")){
            session.setAttribute("username", username);
            return "redirect:/admin";
        }
        else {
            redirectAttributes.addFlashAttribute("check", "Sai tên đăng nhập hoặc mật khẩu");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/logout-admin")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/admin/login";
    }

}
