package com.kaishengit.servlet.admin;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.Page;
import com.kaishengit.vo.UserVo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2017/1/2.
 */
@WebServlet("/admin/user")
public class UserServlet extends BaseServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.parseInt(p):1;
        Page<UserVo> page =userService.findUserList(pageNo);
        req.setAttribute("page",page);
        forward("/admin/user",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userState = Integer.valueOf(req.getParameter("userState"));
        String userid = req.getParameter("userid");
        userState = userState == 1 ? 2:1;
        try {
            userService.updateUserState(userid, userState);
            renderJsonSuccess(resp);
        }catch (ServiceException e){
           renderJsonError(e.getMessage(),resp);
        }
    }

}
