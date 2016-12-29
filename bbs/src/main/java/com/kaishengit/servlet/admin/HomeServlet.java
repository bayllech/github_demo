package com.kaishengit.servlet.admin;

import com.kaishengit.service.AdminService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.vto.HomeView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by bayllech on 2016/12/29.
 */
@WebServlet("/admin/home")
public class HomeServlet extends BaseServlet{
    AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;

        Page<HomeView> page = adminService.homeView(pageNo);
        req.setAttribute("page",page);
        forward("admin/home",req,resp);
    }
}
