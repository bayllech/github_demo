package com.kaishengit.servlet.user;

import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/27.
 */
@WebServlet("/notifyRead")
public class ReadNotifyServelt extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        UserService userService = new UserService();
        userService.updateNotifyStateByIds(ids);
        renderText("success",resp);
    }
}
