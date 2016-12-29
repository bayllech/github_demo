package com.kaishengit.servlet.admin;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.service.AdminService;
import com.kaishengit.service.NodeService;
import com.kaishengit.service.TopicService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by bayllech on 2016/12/29.
 */
@WebServlet("/admin/topic")
public class TopicServlet extends BaseServlet {
    NodeService nodeService = new NodeService();
    TopicService topicService = new TopicService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Node> nodelist = nodeService.findAllNode();
        req.setAttribute("nodeList",nodelist);

        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;

        Page<Topic> page = topicService.findAllTopics(pageNo,"");
        req.setAttribute("page", page);
        forward("admin/topic",req,resp);
    }
}
