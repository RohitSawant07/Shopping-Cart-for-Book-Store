package com.model;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.Customers;

public class CustomersModel {

	static Configuration configuration;
	static SessionFactory sessionFactory;
	static Session hibernatesession = null;
	static Transaction transaction = null;

	public static List<Customers> getUserbyUsername(String username) {

		List<Customers> list = null;
		System.out.println("username fetched is: "+username);

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			TypedQuery<Customers> query = hibernatesession.createQuery("from Customers where username = :username");
			query.setParameter("username", username);
			list = (List<Customers>)query.getResultList();

			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			System.out.println("Error Here");
			e.printStackTrace();
		}
		

		System.out.println(list.size());
		return list;
	}
	
	public static List<Customers> getUserbyUserName(String userName) {

		List<Customers> list = null;

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			TypedQuery<Customers> query = hibernatesession.createQuery("from Customers where name = :userName");
			query.setParameter("userName", userName);
			list = query.getResultList();

			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			System.out.println("Error Here");
			e.printStackTrace();
			return null;
		}

		return list;
	}
	
	public static void insert(Customers customers) {
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();
			
			hibernatesession.save(customers);
			
			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}

}
