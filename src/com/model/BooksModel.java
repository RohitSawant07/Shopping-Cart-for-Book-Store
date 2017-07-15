package com.model;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.Books;
import com.pojo.CustomerDetails;
import com.pojo.Customers;

import sun.awt.image.ImageWatched.Link;

public class BooksModel {
	
	static Configuration configuration;
	static SessionFactory sessionFactory;
	static Session hibernatesession = null;
	static Transaction transaction = null;
	
	public static List<Books> getBookTable() {
		
		List<Books> myBookList = null;
		
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			myBookList = hibernatesession.createQuery("from Books").list();

			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return myBookList;
	}
	
	public static Books getBookByID(int bookID) {
		
		Books books = new Books();
		
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			books = hibernatesession.get(Books.class, bookID);

			transaction.commit();
			hibernatesession.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public static void insertBook(Books book) {

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();
			transaction = hibernatesession.beginTransaction();

			hibernatesession.save(book);

			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

	}
}
