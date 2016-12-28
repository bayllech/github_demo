package com.kaishengit.servlet;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BaseServlet extends HttpServlet {

    public void forward(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/"+path+".jsp").forward(req,resp);
    }

    public void renderText(String str,HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();
    }

    public void renderJSON(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(obj));
        writer.flush();
        writer.close();
    }
    public void renderJsonSuccess(HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("state", "success");
        renderJSON(map,response);
    }
    public void renderJsonSuccess(HttpServletResponse response,Object message) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("state", "success");
        map.put("message",message);
        renderJSON(map,response);
    }
    public void renderJsonError(String message,HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("state", "error");
        map.put("message", message);
        renderJSON(map,response);
    }
    public void renderJsonObject(HttpServletResponse response, Object object) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("state", "success");
        map.put("topic", object);
        renderJSON(map,response);
    }


    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("curr_user");
    }

}
