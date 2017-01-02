package com.kaishengit.servlet.admin;

import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2017/1/2.
 */
@WebServlet("/admin/nodeUpdate")
public class NodeUpdate extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeId = req.getParameter("nodeid");
        TopicService topicService = new TopicService();
        try {
            Node node = topicService.findNodeById(nodeId);
            req.setAttribute("node", node);
            forward("admin/nodeUpdate",req,resp);
        }catch (ServiceException e){
            resp.sendError(404);
        }
    }
}
