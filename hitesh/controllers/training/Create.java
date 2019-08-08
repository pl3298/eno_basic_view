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
import com.cbp.api.models.Trainer;
import com.cbp.api.models.Training;
import matrix.db.Context;

@WebServlet("/training/create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("context") != null) {
			RequestDispatcher create = request.getRequestDispatcher("/WEB-INF/jspfiles/training/create.jsp");
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
			String topic = request.getParameter("topic");
			String duration = request.getParameter("duration");
			//TODO fetch trainer
			String trainerName = request.getParameter("trainer_name");
			System.out.println(trainerName);
			//check for 'null' trainer
			if(trainerName == null || trainerName == "" || trainerName == "null") {
				request.setAttribute("error", "select trainer");
				RequestDispatcher create = request.getRequestDispatcher("/WEB-INF/jspfiles/training/create.jsp");
				create.forward(request, response);
			} else {
				Trainer trainer = new Trainer(Trainer.TYPE, trainerName, "A");

				Training training = new Training(Training.TYPE, name, "A", topic, duration);
				training.setTrainer(trainer);
				training = TrainingsDao.createTraining(context, training);
				if(training != null) {
					request.setAttribute("error", "created successfully");
				} else {
					request.setAttribute("error", "error occured");
				}
				RequestDispatcher create = request.getRequestDispatcher("/WEB-INF/jspfiles/training/create.jsp");
				create.forward(request, response);
			}
		} else {
			response.sendRedirect("../login");
		}
	}

}
