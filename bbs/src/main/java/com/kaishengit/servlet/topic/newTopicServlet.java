package com.kaishengit.servlet.topic;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.NodeService;
import com.kaishengit.service.TopicService;
import com.kaishengit.service.UserService;
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
 * Created by bayllech on 2016/12/21.
 */
@WebServlet("/newTopic")
public class newTopicServlet extends BaseServlet {
    TopicService topicService = new TopicService();
    NodeService nodeService = new NodeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Auth auth = Auth.create(Config.get("ak"), Config.get("sk"));
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody","{ \"success\": true,\"file_path\": \""+Config.get("domain")+"${key}\"}");
        String token = auth.uploadToken(Config.get("bucket"), null, 3600, stringMap);
        req.setAttribute("token", token);

        List<Node> nodelist = nodeService.findAllNode();
        req.setAttribute("nodelist",nodelist);
        forward("topic/newTopic",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("nodeid");
        User user = getCurrentUser(req);

        Node node = nodeService.findNodeById(Integer.valueOf(nodeid));
        nodeService.updateNode(node);
        Topic topic = topicService.addTopic(title,content,Integer.valueOf(nodeid),user.getId());

        renderJsonObject(resp,topic);
    }
}
