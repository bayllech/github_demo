package com.kaishengit.servlet;

import com.kaishengit.entity.Admin;
import com.kaishengit.service.AdminSevice;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "";
        String signin = request.getParameter("signin");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
                break;
            }
        }

        if (signin != null) {
            request.setAttribute("success","账号注册成功，请登录");
        }

        request.setAttribute("name",username);
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String remeberme = request.getParameter("remeberme");
        String code = request.getParameter("code");

        String callback = request.getParameter("callback");
//        System.out.println("登录获取callback:" + callback);

        try {
            //判断验证码是否正确
            HttpSession session = request.getSession();
            String sessionCode = (String) session.getAttribute("patchca");

            if (code == null || !code.equals(sessionCode)) {
                request.setAttribute("name", name);
                request.setAttribute("message", "验证码错误");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            } else {
                //判断用户账号密码是否正确
                AdminSevice adminSevice =new AdminSevice();
                Admin admin = adminSevice.findByName(name,password);
                //将用户保存在session中
                session.setAttribute("admin",admin);
                //保存cookie
                if (StringUtils.isNotEmpty(remeberme)) {
                    Cookie cookie = new Cookie("username",name);
                    cookie.setDomain("localhost");
                    cookie.setPath("/");
                    cookie.setMaxAge(60 * 30 );
                    cookie.setHttpOnly(true);

                    response.addCookie(cookie);
                }

            }

            //是否回到登录前页面
            if (StringUtils.isEmpty(callback)) {
                response.sendRedirect("/list");
            } else {
                response.sendRedirect(callback);
            }

        } catch (Exception e) {
            request.setAttribute("name",name);
            request.setAttribute("message",e.getMessage());

            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
        }
    }

}
