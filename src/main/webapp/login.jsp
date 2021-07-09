<%@page import="bookshop.nurozkaya.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null) {
    	response.sendRedirect("index.jsp");
    }
    %>
    <!-- request.setAttribute("auth", auth); -->
    
<!DOCTYPE html>
<html>

<head>
<title>Shopping Cart Login</title>
<%@include file="includes/head.jsp" %>
<style type="text/css">
body, html {
  height: 100%;
}
.bg {
  /* The image used */
  background-image: url("product-image/bck2.jpg");

  /* Full height */
  height: 100%;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  display:flex;
  justify-content:center;
  align-items:center;
}

</style>
</head>

<body>

<%@include file="includes/navbar.jsp" %>

<div class="bg">
		<div class="card w-50 mx-auto text-white bg-dark border-warning mb-3"  >
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email address</label> 
						<input type="email" name="login-email" class="form-control" placeholder="Enter your email">
					</div>
					<div class="form-group">
						<label>Password</label> 
						<input type="password" name="login-password" class="form-control" placeholder="Enter password">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary border-warning" style="background-color: #52006A">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>