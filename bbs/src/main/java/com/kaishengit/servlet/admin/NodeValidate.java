package com.kaishengit.servlet.admin;

import com.kaishengit.service.NodeService;
import com.kaishengit.servlet.BaseServlet;
import com.kaishengit.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bayllech on 2017/1/2.
 */
@WebServlet("/admin/validateNode")
public class NodeValidate extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NodeService nodeService = new NodeService();
        String nodeId = req.getParameter("nodeid");
        String nodename = req.getParameter("nodename");
        String nodeName = StringUtils.toUtf(nodename);

        if (nodeId == null) {
            String res = nodeService.validateNodeName(nodeName);
            renderText(res,resp);
        } else {
            String res = nodeService.validateNodeName(nodeId,nodeName);
            renderText(res,resp);
        }
    }
}
