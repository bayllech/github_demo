package com.kaishengit.servlet.user;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
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
 * Created by bayllech on 2016/12/15.
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("user/login",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");

        String ip = req.getRemoteAddr();

        Map<String, Object> map = new HashMap<>();
        UserService userService = new UserService();

        try {
            User user = userService.login(name, password, ip);
            HttpSession session = req.getSession();
            session.setAttribute("curr_user",user);
            map.put("state", "success");
        } catch (ServiceException e) {
            map.put("message", e.getMessage());
            map.put("state", "error");
        }
        renderJSON(map,resp);

    }
}
