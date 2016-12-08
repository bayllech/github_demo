package com.kaishengit.servlet;

import com.kaishengit.entity.Admin;
import com.kaishengit.service.AdminSevice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 帅比 on 2016/12/1.
 */
@WebServlet("/signin")
public class SignInAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1456369358206627952L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username == null) {
            req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req,resp);
        } else {
            resp.setContentType("application/json;charset=utf-8");
            //查找用户名是否存在
            AdminSevice adminSevice = new AdminSevice();
            Admin admin = adminSevice.findByName(username);
            PrintWriter out = resp.getWriter();
            if (admin == null) {
                out.print("true");
            } else {
                out.print("false");
            }
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("application/json;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
       // System.out.println(password);

        AdminSevice adminSevice = new AdminSevice();
        //保存账号密码，并登录
        adminSevice.addAdmin(username, password);
        resp.sendRedirect("/login?signin=200");

       /* Map<String, Object> result = new HashMap<>();
        result.put("state", "success");

        String json = new Gson().toJson(result);
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();*/

    }
}
