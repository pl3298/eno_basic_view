package com.cbp.web.controllers.trainer;

import java.io.IOException;

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

@WebServlet("/trainer/create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("context") != null) {
			RequestDispatcher create = request.getRequestDispatcher("/WEB-INF/jspfiles/trainer/create.jsp");
			create.forward(request, response);
		} else {
			response.sendRedirect("../login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("context") != null) {
			Context context = (Context) session.getAttribute("context");
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			Trainer trainer = new Trainer(Trainer.TYPE, name, "A", description);
			trainer = TrainersDao.createTrainer(context, trainer);
			if(trainer == null) {
				request.setAttribute("error", "error occured");
			} else {
				request.setAttribute("error", "created successfully");
			}
			RequestDispatcher create = request.getRequestDispatcher("/WEB-INF/jspfiles/trainer/create.jsp");
			create.forward(request, response);
		} else {
			response.sendRedirect("../login");
		}
	}

}
