package com.kaishengit.servlet.user;

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
@WebServlet("/forgetPassword")
public class ForgetPasswordServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("user/forgetPassword",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String value = req.getParameter("value");

       /* HttpSession session = req.getSession();
        String sessionId = session.getId();*/
       String sessionId = req.getSession().getId();

        Map<String ,Object> map = new HashMap<String ,Object>();

        try {
            UserService userService = new UserService();
            userService.forgetPassword(sessionId,type,value);
            map.put("state", "success");
        } catch (ServiceException e) {
            map.put("state", "error");
            map.put("message",e.getMessage());
        }
        renderJSON(map,resp);

    }
}
