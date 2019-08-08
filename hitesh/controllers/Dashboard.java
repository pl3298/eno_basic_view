package com.cbp.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cbp.api.dao.TrainingsDao;
import com.cbp.api.models.Training;

import matrix.db.Context;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("context") != null) {
			Context context = (Context) request.getSession().getAttribute("context");
			ArrayList<Training> temp_trainings = TrainingsDao.getTrainings(context);
			ArrayList<Training> trainings = new ArrayList<Training>();
			
			for(Training t: temp_trainings) {
				trainings.add(TrainingsDao.getTrainer(context, t));
			}
			
			request.setAttribute("trainings", trainings);
			RequestDispatcher login = request.getRequestDispatcher("/WEB-INF/jspfiles/dashboard.jsp");
			login.forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
