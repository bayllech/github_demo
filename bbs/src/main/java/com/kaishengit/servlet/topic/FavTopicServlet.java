package com.kaishengit.servlet.topic;

import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.TopicService;
import com.kaishengit.servlet.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2016/12/24.
 */
@WebServlet("/favTopic")
public class FavTopicServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicId = req.getParameter("topicid");
        String action = req.getParameter("action");
        TopicService topicService = new TopicService();
        User user = getCurrentUser(req);
        Topic topic = null;

        if (StringUtils.isNotEmpty(action) && StringUtils.isNumeric(topicId)) {
            if (action.equals("fav")) {
                topic = topicService.fav(user.getId(),topicId);
            } else if (action.equals("unfav")) {
                topic = topicService.unfav(user.getId(), topicId);
            }
//            Topic topic = topicService.findTopicById(topicId);
            renderJsonObject(resp,topic);
        } else {
            renderJsonError("参数异常",resp);
        }
    }
}
