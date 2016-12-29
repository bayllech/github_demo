package com.kaishengit.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by bayllech on 2016/12/29.
 */
public class AdminFilter extends EncodingFilter {
    private List<String> excludeList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludePages = filterConfig.getInitParameter("excludePages");
        excludeList = Arrays.asList(excludePages.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (excludeList.contains(request.getRequestURI()) || request.getSession().getAttribute("curr_admin") != null) {
            filterChain.doFilter(request,response);
        } else {
            response.sendRedirect("/admin/login");
           /* if (request.getSession().getAttribute("curr_admin") == null) {
                response.sendRedirect("/admin/login");
            } else {
                filterChain.doFilter(request,response);
            }*/
        }
    }
}
