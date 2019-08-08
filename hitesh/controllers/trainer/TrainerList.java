package com.cbp.web.controllers.trainer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbp.api.dao.TrainersDao;
import com.cbp.api.models.Trainer;

import matrix.db.Context;

@WebServlet("/trainers")
public class TrainerList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("context") != null) {
			//get trainers
			//get reuesting jsp
			String action = request.getParameter("action");
			String name = request.getParameter("name");
			Context context = (Context) session.getAttribute("context");
			ArrayList<Trainer> trainers = TrainersDao.getTrainers(context);
			request.setAttribute("trainers", trainers);
			request.setAttribute("action", action);
			request.setAttribute("name", name);
			RequestDispatcher trainers1 = request.getRequestDispatcher("/WEB-INF/jspfiles/trainer/trainers.jsp");
			trainers1.forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO
		//may be
	}

}
