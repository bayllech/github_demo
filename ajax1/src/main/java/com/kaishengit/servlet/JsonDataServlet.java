/*
package com.kaishengit.servlet;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

*/
/**
 * Created by 帅比 on 2016/12/6.
 *//*

@WebServlet("/data.json")  //细节，细节，细节
public class JsonDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //language=JSON
        //String json = "{\"name\":\"张三\",\"address\":\"usa\"}";

        User user1 = new User(1, "张三", "北京");
        User user2 = new User(12, "Lisa", "上海");
        User user3 = new User(34, "tom", "郑州");

        List<User> userList = Arrays.asList(user1, user2, user3);

        String json = new Gson().toJson(userList);

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
*/
