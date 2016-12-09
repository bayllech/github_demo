package com.kaishengit.servlet;

import com.kaishengit.entity.Message;
import com.kaishengit.service.MessageService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 帅比 on 2016/12/9.
 */
@WebServlet("/timeline")
public class TimeLineServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maxId = req.getParameter("maxId");
        int id = 0;
        if (StringUtils.isNumeric(maxId)) {
            id = Integer.parseInt(maxId);
        }

        MessageService messageService = new MessageService();
        List<Message> messageList = messageService.findByMaxId(id);
        renderJson(messageList,resp);
    }
}
