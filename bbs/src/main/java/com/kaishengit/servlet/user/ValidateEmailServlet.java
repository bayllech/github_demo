package com.kaishengit.servlet.user;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/15.
 */
@WebServlet("/validate/email")
public class ValidateEmailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String type = req.getParameter("type");
        if (StringUtils.isNotEmpty(type) && "1".equals(type)) {
            User user = getCurrentUser(req);
            if (user != null && user.getEmail().equals(email)) {
                renderText("true",resp);
                return;
            }
        }

        UserService userService = new UserService();
        User user = userService.findUserByEmail(email);
        if (user == null) {
            renderText("true", resp);
        } else {
            renderText("false", resp);
        }
    }
}
