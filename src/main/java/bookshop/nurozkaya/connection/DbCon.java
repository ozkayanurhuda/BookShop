package bookshop.nurozkaya.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DB connection with Derby Database

public class DbCon {
	private static Connection connection=null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		if(connection==null) {
			
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection=DriverManager.getConnection("jdbc:derby:C:\\Users\\02485460\\bookshop;create=true");
			System.out.println("Derby Connected");			
		}	
		return connection;
	}
}
