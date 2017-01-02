package com.kaishengit.servlet.admin;

import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.NodeService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by bayllech on 2017/1/2.
 */
@WebServlet("/admin/node")
public class NodeServlet extends BaseServlet {
    NodeService nodeService = new NodeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Node> nodeList = nodeService.findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("admin/node",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String nodeid = req.getParameter("nodeid");
        String nodeName = req.getParameter("nodename");
//        String nodeName = StringUtils.toUtf(nodename);

        if ("del".equals(action)) {
            delNode(nodeid,resp);
        }
        if ("update".equals(action)) {
            updateNode(nodeid,nodeName,resp);
        }

    }

    private void updateNode(String nodeid, String nodeName,HttpServletResponse response) throws IOException {
        try {
            nodeService.updateNode(nodeid, nodeName);
            renderJsonSuccess(response);
        } catch (ServiceException e) {
            renderJsonError(e.getMessage(),response);
        }
    }

    private void delNode(String nodeid,HttpServletResponse response) throws IOException {
        try {
            nodeService.delNode(nodeid);
            renderJsonSuccess(response);
        } catch (ServiceException e) {
            renderJsonError(e.getMessage(),response);
        }
    }
}
