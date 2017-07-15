<%@page import="com.model.CustomerDetailsModel"%>
<%@page import="com.pojo.CustomerDetails"%>
<%@page import="com.pojo.Books"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="asset/css/bootstrap.min.css">
<script type="text/javascript" src="asset/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Orders</title>
</head>
<body>
	<%
		session = request.getSession();
		String loggedinusername = (String) session.getAttribute("loggedinadmin");
		if (loggedinusername == null) {
			response.sendRedirect("admin-login.jsp");
		}
	%>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="index.jsp" class="navbar-brand navbar-link">Shopping Cart - Book Store</a>
			<button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="navcol-1">
			<ul class="nav navbar-nav navbar-right">
				<li role="presentation"><a href="updatecart.jsp">Update Cart?</a></li>
				<li role="presentation"><a href="LogOutController">Logout <%=loggedinusername%>
				</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<%
		List<CustomerDetails> orderList = CustomerDetailsModel.GetDetails();
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<table class="table">
					<tr>
						<th>Order ID</th>
						<th>Book ID</th>
						<th>Customer Name</th>
						<th>Phone Number</th>
						<th>Address</th>
						<th>Quantity</th>
						<th>Particular Total</th>
						<th>Order Date</th>
					</tr>

					<c:forEach var="a" items="<%=orderList%>">
						<tr>
							<th>${a.orderId}</th>
							<th>${a.books.booksId}</th>
							<th>${a.customerName}</th>
							<th>${a.phone}</th>
							<th>${a.address}</th>
							<th>${a.quantity}</th>
							<th>${a.subtotal}</th>
							<th>${a.orderDate}</th>
							<th></th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>