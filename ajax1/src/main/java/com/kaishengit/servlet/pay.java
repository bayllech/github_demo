package com.kaishengit.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 帅比 on 2016/12/14.
 */
@WebServlet("/pay")
public class pay extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = UUID.randomUUID().toString();
        System.out.println("session:"+token);
        HttpSession session = req.getSession();
        session.setAttribute("token",token);

        req.setAttribute("token",token);
        req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        System.out.println("token:"+token);
        String money = req.getParameter("money");

        HttpSession session = req.getSession();
        String sessionToken = (String) session.getAttribute("token");
        System.out.println("sessionToken:"+sessionToken);

        if (sessionToken != null && sessionToken.equals(token)) {
            System.out.println("支付成功：" + money);
            session.removeAttribute("token");

            req.getRequestDispatcher("/WEB-INF/views/paySuccess.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/payError.jsp").forward(req,resp);
        }
    }
}
