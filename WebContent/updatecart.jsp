<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="asset/css/bootstrap.min.css">
<script type="text/javascript" src="asset/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Cart</title>

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
				<li role="presentation"><a href="vieworders.jsp">View Orders!</a></li>
				<li role="presentation"><a href="LogOutController">Logout <%=loggedinusername%></a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<form action="CartUpdateController" method="post">
					<div class="form-group">
						<label for="inputBookName">Book Name</label> <input class="form-control" type="text" id="inputBookName" placeholder="Book Name" name="bookName" />
					</div>
					<div class="form-group">
						<label for="inputAuthor">Author</label> <input class="form-control" type="text" id="inputAuthor" placeholder="Author" name="author" />
					</div>
					<div class="form-group">
						<label for="inputPrice">Price</label> <input class="form-control" type="text" id="inputPrice" placeholder="Price" name="price" />
					</div>
					<button type="submit" class="btn btn-default">Update Cart</button>
				</form>
			</div>
		</div>

	</div>

</body>
</html>