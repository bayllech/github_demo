package com.kaishengit.servlet;

import com.kaishengit.service.AdminSevice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 帅比 on 2016/12/1.
 */
@WebServlet("/signin")
public class SignInAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1456369358206627952L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(password);

        try {
            //查找用户名是否存在
            AdminSevice adminSevice = new AdminSevice();
            adminSevice.findByName(username);

            //保存账号密码，并登录
            adminSevice.addAdmin(username, password);

            resp.sendRedirect("/login?signin=200");
        } catch (Exception e) {
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req,resp);
        }
    }
}
