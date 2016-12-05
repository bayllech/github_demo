package com.kaishengit.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 帅比 on 2016/12/5.
 */
@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        name = new String(name.getBytes("ISO8859-1"), "UTF-8");
        System.out.println("Hello,ajax doget" + name);

        PrintWriter out = resp.getWriter();
        out.print("Hello,ajax");
        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");

        String name = req.getParameter("name");
        PrintWriter out = resp.getWriter();
        if ("tom".equals(name)) {
            System.out.println("账号已被使用");
            out.print("不能用");
        } else {
            System.out.println("账号可用");
            out.print("能用");
        }
        System.out.println("Hello,ajax dopost" + name);

        out.flush();
        out.close();
    }
}
