package com.kaishengit.servlet.topic;

import com.kaishengit.entity.Fav;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
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
import java.util.List;

/**
 * Created by bayllech on 2016/12/21.
 */
@WebServlet("/topicDetail")
public class TopicDetailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        TopicService topicService = new TopicService();
        User user = getCurrentUser(req);
        if (user != null && StringUtils.isNumeric(topicid)) {
            Fav fav = topicService.findFav(user.getId(), topicid);
            req.setAttribute("fav", fav);
        }
        try {
            Topic topic = topicService.findTopicById(topicid);
            //点击数加1
            topic.setClicknum(topic.getClicknum()+1);
            topicService.updateTopic(topic);
            //查找回复列表
            List<Reply> replyList = topicService.replyListByTopicId(topic.getId());
            req.setAttribute("replyList",replyList);

            req.setAttribute("topic", topic);
            forward("topic/topicDetail",req,resp);
        } catch (ServiceException e) {
            resp.sendError(404);
        }
    }
}
