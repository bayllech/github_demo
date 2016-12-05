package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = "";
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("username")) {
					username = cookie.getValue();
					break;
				}
			}
		}
		req.setAttribute("username", username);
		
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String callback = req.getParameter("callback");
		String remeberme = req.getParameter("remeberme");
		
		AdminService adminService = new AdminService();
		
		try {
			Admin admin = adminService.login(username, password);

			if(StringUtils.isNotEmpty(remeberme)) {
				Cookie cookie = new Cookie("username",username);
				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 365 * 100);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
			}
			
			
			
			//��ȡHttpSession
			HttpSession session = req.getSession();
			session.setAttribute("admin", admin);
			if(StringUtils.isEmpty(callback)) {
				resp.sendRedirect("/list");
			} else {
				resp.sendRedirect(callback);
			}
			
			
			
		} catch (ServiceException e) {
			req.setAttribute("message", e.getMessage());
			req.setAttribute("username", username);
			
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
		
		
		
	}
	
	

}
