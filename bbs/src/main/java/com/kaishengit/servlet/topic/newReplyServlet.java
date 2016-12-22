package com.kaishengit.servlet.topic;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.servlet.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/22.
 */
@WebServlet("/newreply")
public class newReplyServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("contents");
        String topicid = req.getParameter("topicid");
        User user = getCurrentUser(req);
        TopicService topicService = new TopicService();

        if (StringUtils.isNumeric(topicid)) {
            try {
                topicService.addReply(content,topicid,user);
                renderJsonSuccess(resp);
            } catch (ServiceException e) {
                renderJsonError(e.getMessage(),resp);
            }
        } else {
            resp.sendError(404);
        }
    }
}
