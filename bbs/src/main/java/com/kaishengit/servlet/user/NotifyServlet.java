package com.kaishengit.servlet.user;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by bayllech on 2016/12/26.
 */
@WebServlet("/notify")
public class NotifyServlet extends BaseServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        List<Notify> notifyList = userService.findNotifyByUser(user);
        req.setAttribute("notifyList",notifyList);
        forward("user/notify",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);

        List<Notify> notifyList = userService.findNotifyByUser(user);
        List<Notify> unreadList = Lists.newArrayList(Collections2.filter(notifyList, new Predicate<Notify>() {
            @Override
            public boolean apply(Notify notify) {
                return notify.getState()==0;
            }
        }));
        renderJsonSuccess(resp,unreadList.size());
    }
}
