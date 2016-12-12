package com.kaishengit.servlet;

import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 帅比 on 2016/12/12.
 */
@WebServlet("/qiniu")
public class QiniuServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "H7AfM8pVk9mCqYWIoWtHQBAmE9fwoqDq7sn-YPZp";
        String sk = "7pO7SVZYrtlFB8le_X8EK_WKPN7DOfEBJIeUR4KI";
        String bucketName = "savedemo";

        Auth auth = Auth.create(ak, sk);

        String token = auth.uploadToken(bucketName);

        req.setAttribute("token", token);
        req.getRequestDispatcher("upload.jsp").forward(req,resp);
    }
}
