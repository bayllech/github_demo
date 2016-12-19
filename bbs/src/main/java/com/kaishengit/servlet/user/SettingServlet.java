package com.kaishengit.servlet.user;

import com.kaishengit.entity.User;
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
 * Created by bayllech on 2016/12/19.
 */
@WebServlet("/setting")
public class SettingServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("user/setting",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        //修改邮箱
        if ("profile".equals(action)) {
            String email = req.getParameter("email");
            User user = getCurrentUser(req);

            if (!user.getEmail().equals(email)) {
                user.setEmail(email);
                user.setState(User.USERSTATE_UNACTIVE);
                UserService userService = new UserService();
                userService.update(user);
                //发送激活邮件验证邮箱
                userService.sendEmail(user.getUsername(),user.getEmail());

                renderJsonSuccess(resp);
            } else {
                renderJsonError("邮箱未更改",resp);
            }
        }

        //修改
    }
}
