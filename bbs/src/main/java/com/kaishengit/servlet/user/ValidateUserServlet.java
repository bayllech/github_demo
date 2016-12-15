package com.kaishengit.servlet.user;

import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/15.
 */
@WebServlet("/validate/user")
public class ValidateUserServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        username = StringUtils.toUtf(username);

        UserService userService = new UserService();
        boolean result = userService.validateUsername(username);
        if (result) {
            renderText("true", resp);
        } else {
            renderText("false",resp);
        }

    }
}
