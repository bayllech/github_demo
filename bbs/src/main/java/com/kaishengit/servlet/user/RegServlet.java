package com.kaishengit.servlet.user;

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
 * Created by bayllech on 2016/12/15.
 */
@WebServlet("/reg")
public class RegServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("user/reg",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Map<String,Object> map = new HashMap();

        try {
            UserService userService = new UserService();
            userService.saveUser(username, password, email, phone);

            map.put("state", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", "error");
            map.put("message", "账号注册失败");
        }
        renderJSON(map,resp);
    }
}
