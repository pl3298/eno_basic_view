package com.cbp.web.controllers.training;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbp.api.dao.TrainingsDao;
import com.cbp.api.models.Training;

import matrix.db.Context;

@WebServlet("/training/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("context") != null) {
			Context context = (Context) session.getAttribute("context");
			String name = request.getParameter("name");
			Training training = new Training(Training.TYPE, name, "A");
			//perform delete
			training = TrainingsDao.deleteTraining(context, training);
			if(training != null) {
				request.setAttribute("error", "deleted successfully");
			} else {
				request.setAttribute("error", "error occured");
			}

			RequestDispatcher delete = request.getRequestDispatcher("/WEB-INF/jspfiles/training/delete.jsp");
			delete.forward(request, response);
		} else {
			response.sendRedirect("../login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
