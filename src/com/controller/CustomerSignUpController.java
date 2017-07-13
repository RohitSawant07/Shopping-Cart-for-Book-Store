package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.Admin;
import com.pojo.Customers;

@WebServlet("/CustomerSignUpController")
public class CustomerSignUpController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String phoneNumber = request.getParameter("phoneNo");
		
		Customers customers = new Customers();
		customers.setName(name);
		customers.setAddress(address);
		customers.setUsername(userName);
		customers.setPassword(passWord);
		customers.setPhonenumber(phoneNumber);
		
		
		Configuration configuration;
		SessionFactory sessionFactory;
		Session session = null;
		Transaction transaction = null;

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();
			
			session.save(customers);
			
			transaction.commit();
			session.close();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("userName", name);
			response.sendRedirect("index.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

	
	}

}
