package com.kaishengit.servlet;

import com.kaishengit.util.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 帅比 on 2016/12/8.
 */
@WebServlet("/rss.xml")
public class rssServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("rssUrl");
        String result = HttpUtil.sendHttpGetRequest(url);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/xml;charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
