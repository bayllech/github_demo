package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Book;
import com.kaishengit.service.BookService;

@WebServlet("/list")
public class ListBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
			BookService bookService = new BookService();
			
			//��ӳɹ�����ѯ���е��鼮������ҳ������ʾ
			List<Book> bookList = bookService.findAllBook();
			req.setAttribute("bookList", bookList);
			
			req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
		
	}

}
