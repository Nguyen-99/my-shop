package com.nuce.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class Logger implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1= (HttpServletRequest) request;
        HttpServletResponse response1= (HttpServletResponse) response;
        String servletPath=request1.getServletPath();
        if(servletPath.equals("/admin/login")){
            chain.doFilter(request1,response1);
            return;
        }
        HttpSession session=request1.getSession();
        String username= (String) session.getAttribute("username");
        if(username!=null){
            chain.doFilter(request,response);
            return;
        }else {
            response1.sendRedirect(request1.getContextPath()+"/admin/login");
        }
    }

    @Override
    public void destroy() {

    }
}
