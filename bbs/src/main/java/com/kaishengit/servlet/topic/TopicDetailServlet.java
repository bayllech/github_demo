package com.kaishengit.servlet.topic;

import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.servlet.BaseServlet;

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
        try {
            Topic topic = topicService.findTopicById(topicid);
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
