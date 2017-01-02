package com.kaishengit.servlet.user;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.Config;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/19.
 */
@WebServlet("/setting")
public class SettingServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Auth auth = Auth.create(Config.get("ak"), Config.get("sk"));
        String token = auth.uploadToken(Config.get("bucket"));
        req.setAttribute("token", token);
        forward("user/setting",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        //修改邮箱
        if ("profile".equals(action)) {
            updateProfile(req,resp);
        }
        //修改密码
        if ("password".equals(action)) {
            updatePassword(req, resp);
        }
        //修改头像
        if ("avatar".equals(action)) {
            updateAvatar(req, resp);
        }
    }

    private void updateAvatar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileKey = req.getParameter("fileKey");
        User user = getCurrentUser(req);
        UserService userService = new UserService();
        userService.updateAvatar(user, fileKey);

        renderJsonSuccess(resp);

    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldpassword = req.getParameter("oldpassword");
        String newpassword = req.getParameter("newpassword");
        User user = getCurrentUser(req);
        UserService userService = new UserService();

        try {
            userService.updatePassword(oldpassword,newpassword, user);
            renderJsonSuccess(resp);
        } catch (ServiceException e) {
            renderJsonError(e.getMessage(),resp);
        }

    }

    private void updateProfile(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        User user = getCurrentUser(req);

        if (!user.getEmail().equals(email)) {
            UserService userService = new UserService();
            userService.updateEmail(user,email);
            //发送激活邮件验证邮箱
            userService.sendEmail(user.getUsername(),user.getEmail());

            renderJsonSuccess(resp);
        } else {
            renderJsonError("邮箱未更改",resp);
        }

    }
}
