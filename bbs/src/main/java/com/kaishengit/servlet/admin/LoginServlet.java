package com.kaishengit.servlet.admin;

import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;
import com.kaishengit.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bayllech on 2016/12/29.
 */
@WebServlet("/admin/login")
public class LoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("curr_admin");
        forward("admin/login",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminName = req.getParameter("adminName");
        String password = req.getParameter("password");

        String ip = req.getRemoteAddr();

        Map<String, Object> map = new HashMap<>();
        AdminService adminService = new AdminService();

        try {
            Admin admin = adminService.adminLogin(adminName, password, ip);
            HttpSession session = req.getSession();
            session.setAttribute("curr_admin",admin);
            map.put("state", "success");
        } catch (ServiceException e) {
            map.put("message", e.getMessage());
            map.put("state", "error");
        }
        renderJSON(map,resp);
    }
}
