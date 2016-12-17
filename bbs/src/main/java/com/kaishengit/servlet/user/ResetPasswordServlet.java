package com.kaishengit.servlet.user;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bayllech on 2016/12/17.
 */
@WebServlet("/forgetPassword/newPassword")
public class ResetPasswordServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if (token == null) {
            resp.sendError(404);
        } else {
            UserService userService = new UserService();
            try {
//                System.out.println(token);
                User user = userService.foundPasswordByToken(token);

                req.setAttribute("user", user);
//                System.out.println(user.getUsername());
                req.setAttribute("token", token);
                forward("user/resetPassword",req,resp);
            } catch (ServiceException e) {
                req.setAttribute("message", e.getMessage());
                forward("user/reset_error",req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        System.out.println(name);
        String password = req.getParameter("password");
        String token = req.getParameter("token");

        Map<String, Object> map = new HashMap<String, Object>();
        UserService userService = new UserService();

        try {
            userService.resetpassword(token, name, password);
            map.put("state", "success");
        } catch (ServiceException e) {
            map.put("state", "error");
            map.put("message", e.getMessage());
        }
        renderJSON(map,resp);
    }
}
