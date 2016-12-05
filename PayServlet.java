package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String money = req.getParameter("money");

        //验证验证码是否正确
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("patchca");

        if(code != null && code.equals(sessionCode)) {
            session.removeAttribute("patchca");
            System.out.println("支付:" + money + "元");
        } else {
            req.setAttribute("message","验证码错误");
            req.setAttribute("money",money);
            req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req,resp);
        }


    }
}
