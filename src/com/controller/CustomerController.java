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

import com.model.CustomersModel;
import com.pojo.Admin;
import com.pojo.Customers;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		List<Customers> list = CustomersModel.getUserbyUsername(username);

		if (list.size() > 0) {
			HttpSession httpSession = request.getSession();
			String userName = list.get(0).getName();
			httpSession.setAttribute("userName", userName);
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("customer.html");
		}

		
	}

}
