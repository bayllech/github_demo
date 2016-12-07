package com.kaishengit.servlet;

import com.kaishengit.entity.Message;
import com.kaishengit.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String message = request.getParameter("message");
        String name = request.getParameter("name");

        Message mess = new Message();
        mess.setId(Integer.valueOf(id));
        mess.setName(name);
        mess.setMessage(message);

        MessageService bookService = new MessageService();

        bookService.updateBook(mess);
        response.sendRedirect("/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        MessageService bookService = new MessageService();
        Message book = bookService.findById(id);
        //查找结束
        request.setAttribute("book",book);
        request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request,response);

    }

}
