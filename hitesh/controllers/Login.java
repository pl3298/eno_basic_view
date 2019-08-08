package com.cbp.web.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cbp.api.util.ContextBuilder;

import matrix.db.Context;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("context") != null) {
			response.sendRedirect("dashboard");
		} else {
			RequestDispatcher login = request.getRequestDispatcher("/WEB-INF/jspfiles/login.jsp");
			login.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//create context && validate for
		RequestDispatcher login = request.getRequestDispatcher("/WEB-INF/jspfiles/login.jsp");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String vault = request.getParameter("vault");
		
		Context context = ContextBuilder.getContext(userid, password, vault); //TODO should be passing userid
		if(context != null) {
			request.getSession().setAttribute("context", context);
			response.sendRedirect("dashboard");
		} else {
			request.setAttribute("error", "invalid credential");
			login.forward(request, response);
		}
	}
}
