package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.pojo.Customers;
import com.pojo.Item;

@WebServlet("/CartController")
public class CartController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		int bookId = Integer.parseInt(request.getParameter("id"));
		
		Configuration configuration;
		SessionFactory sessionFactory;
		Session hibernatesession = null;
		Transaction transaction = null;
		Books books = null;
		Customers customer = null;

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			books = hibernatesession.get(Books.class, bookId);

			transaction.commit();
			hibernatesession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		Item item = new Item();
		
		if (action.equals("ordernow")) {
			if (session.getAttribute("cart") == null) {
				List<Item> cart = new ArrayList<Item>();
				cart.add(new Item(books, 1));
				session.setAttribute("cart", cart);
			} else {
				List<Item> cart = (List<Item>)session.getAttribute("cart");
				int index = findIndex(bookId, cart);
				if (index == -1) {
					cart.add(new Item(books, 1));
				} else {
//					int quantity = cart.get(index).getQuantity() + 1;
					cart.get(index).setQuantity(cart.get(index).getQuantity() + 1);
				}
				session.setAttribute("cart", cart);
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if (action.equals("delete")) {
			List<Item> cart = (List<Item>)session.getAttribute("cart");
			int index = findIndex(bookId, cart);
			cart.remove(index);
			session.setAttribute("cart", cart);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} 
		else if (action.equals("empty")) {
			List<Item> cart = (List<Item>)session.getAttribute("cart");
			cart = new ArrayList<Item>();
			session.setAttribute("cart", cart);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} 
		else if (action.equals("checkout")){
		
			List<Item> cart = (List<Item>)session.getAttribute("cart");
			session.setAttribute("cart", cart);
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
			
		}
		else if (action.equals("placeorder")) {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNo");
			List<Item> cart = (List<Item>)session.getAttribute("cart");
			session.setAttribute("cart", cart);
			request.getRequestDispatcher("CheckoutController.jsp").forward(request, response);
		}
		
	}

	private int findIndex(int id, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getBooks().getBooksId() == id) {
				return i;
			}
		}
		return -1;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
