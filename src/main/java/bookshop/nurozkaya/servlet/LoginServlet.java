package bookshop.nurozkaya.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.nurozkaya.connection.DbCon;
import bookshop.nurozkaya.dao.UserDao;
import bookshop.nurozkaya.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			//out.print("login servlet");
			//getParameter of login.jsp input forms names login-email
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");
			//out.print(email+password);
			
			try { //UserDao ya gönder params/ userDao-userLogin
				UserDao udao = new UserDao(DbCon.getConnection());
				User user = udao.userLogin(email, password);
				
				if(user!=null) {
					//out.print("user login");
					//set this user value in session to using next
					request.getSession().setAttribute("auth", user);
					//send user to index page				
					response.sendRedirect("index.jsp");
				} else {
					out.println("<h3 style='color:crimson; text-align: center'>LOGIN FAILED! <a href='index.jsp'>GO BACK HOME</a><br></br><img class='mx-auto d-block' ; src='product-image/error.png'></img></h3>");
				}
			}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
