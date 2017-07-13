package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.lang.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.Admin;

@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");

		Configuration configuration;
		SessionFactory sessionFactory;
		Session session = null;
		Transaction transaction = null;

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();

			Admin admin = (Admin) session.get(Admin.class, username);

			transaction.commit();
			session.close();

			if (admin == null) {
				response.sendRedirect("admin-login.jsp");
			}

			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("loggedinadmin", username);
			response.sendRedirect("updatecart.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
