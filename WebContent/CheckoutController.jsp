<%@page import="com.pojo.Customers"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="com.pojo.Books"%>
<%@page import="com.pojo.CustomerDetails"%>
<%@page import="java.util.Date"%>
<%@page import="org.hibernate.*"%>
<%@page import="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="asset/css/bootstrap.min.css">
<script type="text/javascript" src="asset/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="asset/css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Placed</title>
</head>
<body>
<%
		session = request.getSession();
		String userName = (String) session.getAttribute("userName");

		if (userName == null) {
			response.sendRedirect("index.jsp");
		} %>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="index.jsp" class="navbar-brand navbar-link">Shopping
				Cart - Book Store</a>
			<button class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
					class="icon-bar"></span><span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="navcol-1">
			<ul class="nav navbar-nav navbar-right">
				<li role="presentation"><a href="#">Hello <%=userName%> !</a></li>
				<li role="presentation"><a href="LogOutController">Logout?</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<%
		String name = null;
		String address = null;
		String phoneNumber = null;
		Date order_date = new Date();

		Configuration configuration;
		SessionFactory sessionFactory;
		Session hibernatesession = null;
		Transaction transaction = null;

		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			hibernatesession = sessionFactory.openSession();

			transaction = hibernatesession.beginTransaction();

			TypedQuery<Customers> query = hibernatesession.createQuery("from Customers where name = :userName");
			query.setParameter("userName", userName);
			List<Customers> list = query.getResultList();
			

			name = list.get(0).getName();
			address = list.get(0).getAddress();
			phoneNumber = list.get(0).getPhonenumber();

			transaction.commit();
			hibernatesession.close();

		} catch (Exception e) {
			System.out.println("Error Here");
			e.printStackTrace();
		}

		CustomerDetails customerDetails = new CustomerDetails();

		customerDetails.setCustomerName(name);
		customerDetails.setAddress(address);
		customerDetails.setPhone(phoneNumber);
		customerDetails.setOrderDate(order_date);

		List<Books> myBookList = null;
	%>

	<c:forEach var="it" items="${sessionScope.cart}">

		<c:set var="BookID" value="${it.books.booksId}" scope="session"></c:set>
		<c:set var="BookName" value="${it.books.booksName }" scope="session"></c:set>
		<c:set var="Price" value="${it.books.price }" scope="session"></c:set>
		<c:set var="Quantity" value="${it.quantity }" scope="session"></c:set>
		<c:set var="SubTotal" value="${it.books.price * it.quantity }"
			scope="session"></c:set>

		<%
				String bookName = String.valueOf(session.getAttribute("BookName"));
				int price = Integer.parseInt(String.valueOf(session.getAttribute("Price")));
				int order_id = (int) (Math.random() * (Math.abs(1 - 500) + 1)) + (1 <= 500 ? 1 : 500);
				
				
				customerDetails.setOrderId(order_id);
				customerDetails.setQuantity(Integer.parseInt(String.valueOf(session.getAttribute("Quantity"))));
				
				Books books = new Books();
				books.setBooksId(Integer.parseInt(String.valueOf(session.getAttribute("BookID"))));
				customerDetails.setBooks(books);
				
				
				customerDetails.setSubtotal(Integer.parseInt(String.valueOf(session.getAttribute("SubTotal"))));

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
		%>
	</c:forEach>
	<div class="container">
		<div class="row">
			<p><h3>Hi <%=userName%>!! Your Order has been confirmed. The Books shall be delivered within 2 Days</h3></p>
			<a class="btn btn-default" href="CartController?id=0&action=empty">Continue Shopping</a>
		</div>
	</div>
</body>
</html>