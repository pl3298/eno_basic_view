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

@WebServlet("/training/modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("context") != null) {
			Context context = (Context) session.getAttribute("context");
			//TODO get trainer
			String name = request.getParameter("name");
			Training training = new Training(Training.TYPE, name, "A");
			training = TrainingsDao.getTraining(context, training);
			training = TrainingsDao.getTrainer(context, training);
			System.out.println(training.getTrainer());
			request.setAttribute("training", training);
			if(request.getParameter("trainer") == null) {
				request.setAttribute("trainer", "");
			} else {
				request.setAttribute("trainer", request.getParameter("trainer"));
			}
			RequestDispatcher modify = request.getRequestDispatcher("/WEB-INF/jspfiles/training/modify.jsp");
			modify.forward(request, response);
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
			Trainer trainer = new Trainer(Trainer.TYPE, trainerName, "A");
			Training training = new Training(Training.TYPE, name, "A", topic, duration);
			training.setTrainer(trainer);
			
			training = TrainingsDao.updateTraining(context, training, trainer);
			if(training == null) {
				request.setAttribute("error", "error occured");
			} else {
				request.setAttribute("error", "updated successfully");
			}
			RequestDispatcher create = request.getRequestDispatcher("/WEB-INF/jspfiles/training/create.jsp");
			create.forward(request, response);
		} else {
			response.sendRedirect("../login");
		}
	}

}
