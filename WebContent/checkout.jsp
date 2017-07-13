<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="asset/css/bootstrap.min.css">
<script type="text/javascript" src="asset/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="asset/css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Out Page</title>
</head>
<body>
	<%
		session = request.getSession();
		String userName = (String) session.getAttribute("userName");

		if (userName == null) {
			response.sendRedirect("index.html");
		}
	%>
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

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
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
							<td><a
								href="CartController?id=${it.books.booksId }&action=delete"
								onclick="return confirm('Confirm Delete?')">Delete</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5" align="right">Total</td>
						<td>${s }</td>
					</tr>
				</table>
				<a class="btn btn-default float-right"
					href="CartController?id=0&action=empty">Cancel</a> <a
					href="CheckoutController.jsp"> Purchase as <%=userName%>?
				</a>
			</div>
		</div>
	</div>
</body>
</html>