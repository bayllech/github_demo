package com.kaishengit.servlet;

import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Message;
import com.kaishengit.service.AdminSevice;
import com.kaishengit.service.MessageService;
import com.kaishengit.util.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/list")
public class GuestList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        String p = request.getParameter("p");
        //判断用户是否登录
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");

        if (admin == null) {
            response.sendRedirect("/login");
        } else if ("iloveqibei".equals(admin.getName())) {
            int pageNo = 1;
            if (StringUtils.isNumeric(p)) {
                pageNo = Integer.parseInt(p);
            }
            MessageService messageService = new MessageService();
            Page<Message> bookPage = messageService.findAll(pageNo);

            request.setAttribute("bookPage",bookPage);

            request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request,response);

            } else {
                int pageNo = 1;
                if (StringUtils.isNumeric(p)) {    //p参数不需加引号，坑了好久。。。
                    pageNo = Integer.parseInt(p);  //这个p也是，不用引号。。。
                }

                MessageService messageService = new MessageService();
                Page<Message> bookPage = messageService.findByPageNo(pageNo,admin);

                request.setAttribute("bookPage",bookPage);

                request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request,response);
            }

    }

}
