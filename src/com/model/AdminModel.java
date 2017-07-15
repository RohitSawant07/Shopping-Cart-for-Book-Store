package com.model;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.Admin;

public class AdminModel {
	
	static Configuration configuration;
	static SessionFactory sessionFactory;
	static Session session = null;
	static Transaction transaction = null;
	
	public static Admin getUserNameRow(String username) {
		
		Admin admin = null;
		
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();

			admin = (Admin) session.get(Admin.class, username);

			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

}
