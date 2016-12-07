package com.kaishengit.filter;

import com.kaishengit.entity.Admin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ValidateFilter extends AbstractFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        System.out.println("获取uri:" + uri);
        if ("/".equals(uri) || "/signin".equals(uri) ||"/favicon.ico".equals(uri) || "/patchca.png".equals(uri) || "/login".equals(uri) || "/index.jsp".equals(uri) || uri.startsWith("/static/")) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null) {
                filterChain.doFilter(request, response);
            } else {
                System.out.println("重定向：" + uri);
                response.sendRedirect("/login?callback=" + uri);
            }
        }
    }
}
