package cn.edu.zhku.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.service.AdminService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
		if(username != null && password != null){
			if(as.checkLogin(username,password)){
				request.getSession().setAttribute("login", "ok");
				rd = request.getRequestDispatcher("/as/list");
				rd.forward(request, response);
				return;
			}
		}else{
			rd = request.getRequestDispatcher("/admin/login.jsp?error = yes");
			rd.forward(request, response);
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
