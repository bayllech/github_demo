package com.kaishengit.servlet.user;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/17.
 */
@WebServlet("/active/user")
public class ActiveUserServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("_");
        if (StringUtils.isEmpty(token)) {
            resp.sendError(404);
        } else {
            UserService userService = new UserService();
            try {
                userService.activeUser(token);
                forward("/user/active_success",req,resp);
            } catch (ServiceException e) {
                req.setAttribute("message",e.getMessage());
                forward("/user/active_error",req,resp);
            }
        }
    }
}
