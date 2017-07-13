package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.Admin;
import com.pojo.Customers;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");
		
		System.out.println(username);
		System.out.println(password);
		
		Configuration configuration;
		SessionFactory sessionFactory;
		Session session = null;
		Transaction transaction = null;
		

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();
			
			TypedQuery<Customers> query = session.createQuery("from Customers where username = :username");
			query.setParameter("username", username);
			List<Customers> list = query.getResultList();
			
			transaction.commit();
			session.close();
			
			if (list.isEmpty()) {
				response.sendRedirect("customer.html");
			}
			
			HttpSession httpSession = request.getSession();
			String userName = list.get(0).getName();
			httpSession.setAttribute("userName", userName);
			response.sendRedirect("index.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
