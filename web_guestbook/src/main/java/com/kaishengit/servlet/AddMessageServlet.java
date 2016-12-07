package com.kaishengit.servlet;

import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Message;
import com.kaishengit.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 帅比 on 2016/11/23.
 */
@WebServlet("/add")
public class AddMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否登录
        HttpSession session = req.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if (admin == null) {
            resp.sendRedirect("/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String message = req.getParameter("message");
        String name = req.getParameter("name");

        HttpSession session = req.getSession();
        Admin admin = (Admin)session.getAttribute("admin");

        if (admin == null) {
            resp.sendRedirect("/login");
        } else {

            Message mess = new Message();
            mess.setName(name);
            mess.setMessage(message);
            mess.setMid(admin.getId());

            MessageService messageService = new MessageService();
            messageService.addBook(mess);
            resp.sendRedirect("/list");
        }
    }
}
