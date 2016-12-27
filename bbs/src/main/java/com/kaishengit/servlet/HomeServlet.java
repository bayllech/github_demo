package com.kaishengit.servlet;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.service.NodeService;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {
    NodeService nodeService = new NodeService();
    TopicService topicService = new TopicService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Node> nodelist = nodeService.findAllNode();
        req.setAttribute("nodelist",nodelist);

        String nodeid = req.getParameter("nodeid");
        //判断nodeId是否是数字和存在
        String nodeId = nodeService.existNodeId(nodeid);
        req.setAttribute("nodeid",nodeId);

        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        /*if (StringUtils.isNotEmpty(nodeId) && !StringUtils.isNumeric(nodeId)) {
            nodeId=null;
        }*/

        Page<Topic> page = topicService.findAllTopics(pageNo,nodeId);
        req.setAttribute("page", page);

        forward("index",req,resp);
    }

}
