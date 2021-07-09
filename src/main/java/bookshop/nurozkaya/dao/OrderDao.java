package bookshop.nurozkaya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import bookshop.nurozkaya.model.*;

public class OrderDao {
	
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection con) {		
		this.con = con;
	}
	
	//OrderNowServ
	//CheckOutServ
	public boolean insertOrder(Order model) {
		boolean result=false;
		
		//seçilen ürünü almak 
		try {
			
			query="INSERT INTO ORDERS (P_ID, U_ID, O_QUANTITY, O_DATE) VALUES(?,?,?,?)";
			pst=this.con.prepareStatement(query);
			
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			
			pst.executeUpdate();
			result = true;
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;	
	}
	
	//------------------------------Order the orders (o_id desc order)
	//orders.jsp
	public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        
        try {
        	
            query = "SELECT * FROM ORDERS WHERE U_ID=? ORDER BY ORDERS.O_ID DESC";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                //create con from productDao
                ProductDao productDao = new ProductDao(this.con);
                
                int pId = rs.getInt("p_id");
                Product product = productDao.getSingleProduct(pId);
                
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setAuthor(product.getAuthor());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                
                list.add(order);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
//-----------------------------------Cancel Order by OrderId
	//CancelOrdrServ
    public void cancelOrder(int id) {
        //boolean result = false;
        try {
        	
            query = "DELETE FROM ORDERS WHERE O_ID=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}
