package com.kaishengit.servlet.user;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/15.
 */
@WebServlet("/validate/email")
public class ValidateEmailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        UserService userService = new UserService();
        User user = userService.validateEmail(email);
        if (user == null) {
            renderText("true", resp);
        } else {
            renderText("error", resp);
        }
    }
}
