package bookshop.nurozkaya.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import bookshop.nurozkaya.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set the response type
		response.setContentType("text/html;charset=UTF-8");
		//I want to store books in my session as an obj array
		//So implement book inside my session as an obj
		try (PrintWriter out = response.getWriter()) {
			//create obj array list(empty)
			ArrayList<Cart> cartList = new ArrayList<>();
			
			//get product id from url
			int id=Integer.parseInt(request.getParameter("id"));
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1);//by def add one obj 
			
			//Check the session, create a session obj 
			HttpSession session=request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			//check if it is null 
			//add my product to cartlist
			if(cart_list==null) {
				cartList.add(cm);
				//cartList added to our session as a cart-list
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
				
			} else { //if it exists add to cartlist
				cartList=cart_list;
				boolean exist=false;
				
				for(Cart c:cart_list) {
					//if that book already exists in cart print alert and href cart page 
					if(c.getId()==id) {
						exist=true;
						out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>Go to Cart Page</a><br></br><br></br><img class='mx-auto d-block' ; src='product-image/cart2.png'></img></h3>");
						//out.println("<img class='mx-auto d-block' ; src='product-image/cart2.png'></img>");
					}	
				}
				//if that book doesnt exist
				if(!exist) {
					//create an obj and added to cartlist
					cartList.add(cm);		
					response.sendRedirect("index.jsp");
				}
			}		
		}
	}
}