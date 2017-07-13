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

import com.pojo.Books;

@WebServlet("/CartUpdateController")
public class CartUpdateController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpsession = request.getSession();
		String loggedinusername = (String) httpsession.getAttribute("loggedinadmin");
		if (loggedinusername == null) {
			response.sendRedirect("admin-login.jsp");
		}

		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));

		Books book = new Books();
		
		book.setBooksName(bookName);
		book.setAuthor(author);
		book.setPrice(price);

		Configuration configuration;
		SessionFactory sessionFactory;
		Session session = null;
		Transaction transaction = null;

		try {

			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(book);

			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		response.sendRedirect("updatecart.jsp");

	}

}
