package com.cbp.web.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import matrix.db.Context;

@WebServlet("/logout")
public class LogOff extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("context") != null) {
			Context context =  (Context) request.getSession().getAttribute("context");
			context.close();
		}
		request.getSession().invalidate();
		request.setAttribute("error", "Logged off successfully");
		RequestDispatcher login = request.getRequestDispatcher("/WEB-INF/jspfiles/login.jsp");
		login.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
