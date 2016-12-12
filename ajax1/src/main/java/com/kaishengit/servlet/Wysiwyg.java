package com.kaishengit.servlet;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 帅比 on 2016/12/12.
 */
@WebServlet("/wysiwyg")
public class Wysiwyg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "H7AfM8pVk9mCqYWIoWtHQBAmE9fwoqDq7sn-YPZp";
        String sk = "7pO7SVZYrtlFB8le_X8EK_WKPN7DOfEBJIeUR4KI";
        String buckName = "savedemo";

        Auth auth = Auth.create(ak, sk);
        String returnBody = "{\"success\":true,\"file_path\":\"http://ohyf2mhv9.bkt.clouddn.com/${key}?imageView2/2/w/150/h/150\"}";
        StringMap map = new StringMap();
        map.put("returnBody", returnBody);

        String token = auth.uploadToken(buckName, null, 3600, map);
        req.setAttribute("token",token);
        req.getRequestDispatcher("simditor.jsp").forward(req,resp);
    }
}
