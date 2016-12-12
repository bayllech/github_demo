package com.kaishengit.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 帅比 on 2016/12/12.
 */
@WebServlet("/send")
public class SendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
//        resp.setCharacterEncoding("utf-8");
        String message = req.getParameter("message");
        System.out.println(message);

        req.setAttribute("message",message);
        req.getRequestDispatcher("show.jsp").forward(req,resp);
    }
}
