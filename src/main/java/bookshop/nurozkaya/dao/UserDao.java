package bookshop.nurozkaya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bookshop.nurozkaya.model.User;

//DB Connection with User

public class UserDao {
	
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	//------------------------------------login user (login serv)
	public User userLogin(String email, String password) {
		
		User user=null;
		
		//if user exists
		try {
			
			query="SELECT * FROM APP.USERS WHERE EMAIL=? AND PASSWORD=?";
			pst=this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				//create new User
				user= new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}			
		} 		
		catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}		
		return user;		
	}
}
