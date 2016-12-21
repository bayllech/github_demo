package com.kaishengit.servlet.topic;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.TopicService;
import com.kaishengit.service.UserService;
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
@WebServlet("/newTopic")
public class newTopicServlet extends BaseServlet {
    TopicService topicService = new TopicService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Node> nodelist = topicService.findAllNode();
        req.setAttribute("nodelist",nodelist);
        forward("topic/newTopic",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("nodeid");
        User user = getCurrentUser(req);

        Topic topic = topicService.addTopic(title,content,Integer.valueOf(nodeid),user.getId());

        renderJsonObject(resp,topic);
    }
}
