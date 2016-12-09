package com.kaishengit.servlet;

import com.google.gson.Gson;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 给客户端一个json响应
 * Created by 帅比 on 2016/12/9.
 */
public class BaseServlet extends HttpServlet {
    public void renderJson(Object object,HttpServletResponse response) throws IOException {
        String json = new Gson().toJson(object);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
