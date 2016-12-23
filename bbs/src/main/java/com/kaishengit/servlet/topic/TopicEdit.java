package com.kaishengit.servlet.topic;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.NodeService;
import com.kaishengit.service.TopicService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.Config;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by bayllech on 2016/12/23.
 */
@WebServlet("/topicEdit")
public class TopicEdit extends BaseServlet {
    TopicService topicService = new TopicService();
    NodeService nodeService = new NodeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Auth auth = Auth.create(Config.get("ak"), Config.get("sk"));
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody","{ \"success\": true,\"file_path\": \""+Config.get("domain")+"${key}\"}");
        String token = auth.uploadToken(Config.get("bucket"), null, 3600, stringMap);
        req.setAttribute("token", token);

        String topicId = req.getParameter("topicId");
        Topic topic = topicService.findTopicById(topicId);
        req.setAttribute("topic",topic);

        List<Node> nodeList = nodeService.findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("topic/topicEdit",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicId = req.getParameter("topicId");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("nodeid");
        String oldNodeId = req.getParameter("topicNodeId");
        User user = getCurrentUser(req);

        //新主题加一，就主题减一
        if (!oldNodeId.equals(nodeid)) {
            Node newNode = nodeService.findNodeById(Integer.valueOf(nodeid));
            nodeService.addTopicNum(newNode);
            Node oldNode = nodeService.findNodeById(Integer.valueOf(oldNodeId));
            nodeService.subTopicNum(oldNode);
        }

        Topic topic = topicService.updateTopic(title,content,Integer.valueOf(nodeid),topicId);

        renderJsonObject(resp,topic);
    }
}
