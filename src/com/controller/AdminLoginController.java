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

import com.model.AdminModel;
import com.pojo.Admin;

@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");
		
		Admin admin = AdminModel.getUserNameRow(username);

			if (admin == null) {
				response.sendRedirect("admin-login.jsp");
			}

			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("loggedinadmin", username);
			response.sendRedirect("updatecart.jsp");
	}

}
