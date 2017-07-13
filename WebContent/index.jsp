<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.*"%>
<%@page import="com.pojo.Books"%>
<%@page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="asset/css/bootstrap.min.css">
<script type="text/javascript" src="asset/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="asset/css/styles.css">
<title>Book Store - Shopping Cart</title>
</head>
<body>
	<div class="backimage">
		<%
			session = request.getSession();

			String userName = (String) session.getAttribute("userName");
			String disabled = "";

			if (userName == null) {
				userName = "Customer";
				disabled = "disabled";
			}
		%>
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="index.jsp" class="navbar-brand navbar-link">Shopping Cart - Book Store</a>
				<button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="nav navbar-nav navbar-right">
					<li role="presentation"><a href="admin-login.jsp">Admin</a></li>
					<li role="presentation"><a href="#">Hello <%=userName%> !</a></li>
					<li role="presentation"><a href="LogOutController">Logout?</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<%
			Configuration configuration;
			SessionFactory sessionFactory;
			Session hibernatesession = null;
			Transaction transaction = null;
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
		%>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6 float-left">
					<table class="table">
						<tr>
							<th>Book ID</th>
							<th>Book Name</th>
							<th>Author</th>
							<th>Price</th>
							<th>Buy</th>
						</tr>
						<c:forEach var="p" items="<%=myBookList%>">
							<tr>
								<td>${p.booksId}</td>
								<td>${p.booksName }</td>
								<td>${p.author}</td>
								<td>${p.price}</td>
								<td align="center"><a href="CartController?id=${p.booksId}&action=ordernow">Add To Cart</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="col-md-6 float-right">
					<table class="table">
						<tr>
							<th>Book Name</th>
							<th>Book Author</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Sub Total</th>
							<th>Option</th>
						</tr>
						<c:set var="s" value="0"></c:set>
						<c:forEach var="it" items="${sessionScope.cart}">
							<c:set var="s" value="${s + it.books.price * it.quantity }"></c:set>
							<tr>
								<td>${it.books.booksName }</td>
								<td>${it.books.author }</td>
								<td>${it.books.price }</td>
								<td>${it.quantity }</td>
								<td>${it.books.price * it.quantity }</td>
								<td><a href="CartController?id=${it.books.booksId }&action=delete" onclick="return confirm('Confirm Delete?')">Delete</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4" align="right">Total</td>
							<td>${s }</td>
						</tr>
					</table>
					<a class="btn btn-default float-right" href="CartController?id=0&action=empty">Clear Cart</a>
					<p></p>
					<a class="btn btn-default float-right" href="CartController?id=0&action=checkout" <%=disabled%>>Check Out</a>
					<p>
						Check Out disabled? <a href="customer.html">Try Login</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>