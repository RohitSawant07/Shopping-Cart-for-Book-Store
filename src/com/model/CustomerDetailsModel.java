package com.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.CustomerDetails;

public class CustomerDetailsModel {

	static Configuration configuration;
	static SessionFactory sessionFactory;
	static Session hibernatesession = null;
	static Transaction transaction = null;

	public static void saveCustomerDetails(CustomerDetails customerDetails) {

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			hibernatesession.save(customerDetails);

			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	public static List<CustomerDetails> GetDetails() {
		
		List<CustomerDetails> orderList = null;
		
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			orderList = hibernatesession.createQuery("from CustomerDetails").list();

			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	return orderList;
	}

}
