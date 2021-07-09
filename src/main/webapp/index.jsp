<%@page import="bookshop.nurozkaya.connection.*"%>
<%@page import="bookshop.nurozkaya.dao.*"%>
<%@page import="bookshop.nurozkaya.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>

<head>
<%@include file="/includes/head.jsp"%>

<title>BookLand</title>
</head>

<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3" >All Books</div>
		<div class="row">
		
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-image/<%=p.getImage() %>" height="360px" alt="Card image cap">
					
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="card-subtitle mb-2 text-muted"><%=p.getAuthor() %></h6>
						<h6 class="price">Price: $<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						
						<div class="mt-3 d-flex justify-content-between">
						<!--secilen kitap id yi alıp carta ekle   -->
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> 
							<a class="btn btn-danger" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no products.");
			}
			%>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>