<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="asset/css/bootstrap.min.css">
<script type="text/javascript" src="asset/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
</head>
<body>

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
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<form action="AdminLoginController" method="post">
					<div class="form-group">
						<label for="inputUsername">User Name</label> <input class="form-control" type="text" id="inputUsername" placeholder="User Name" name="userName" />
					</div>
					<div class="form-group">
						<label for="inputPass">Password</label> <input class="form-control" type="password" id="inputPass" placeholder="Password" name="passWord" />
					</div>
					<button type="submit" class="btn btn-default">Login</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>