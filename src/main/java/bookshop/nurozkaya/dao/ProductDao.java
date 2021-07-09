package bookshop.nurozkaya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import bookshop.nurozkaya.model.Cart;
import bookshop.nurozkaya.model.Product;

//take books as list 

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	//-------------------get all products index.jsp
	public List<Product> getAllProducts() {
		
		List<Product> book= new ArrayList<>();
		
		try {
			query="SELECT * FROM APP.PRODUCTS";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			//check if there is any value in resultset
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setAuthor(rs.getString("author"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				book.add(row);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}				
		return book;
	}
	//----------------select product from selected product id
	// directly buy a single product 
	// called from OrderDao  
	public Product getSingleProduct(int id) {
		Product row = null;
		
		//seçilen id li booku al 
		try {
			
			query="SELECT * FROM PRODUCTS WHERE ID=?";
			
			pst= this.con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
            	row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setAuthor(rs.getString("author"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
            }		
		} 
		catch(Exception e ) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
	
	//--------------------take product price from db
	//called from cart.jsp
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum=0;
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					
					query = "SELECT PRICE FROM PRODUCTS WHERE ID=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs= pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}		
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sum;
	}
	
	//-------------------get all cart products
	//called from cart.jsp
	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		
		List<Cart> book = new ArrayList<>();
		
		 try {
			  if (cartList.size() > 0) {
	                for (Cart item : cartList) {
	                	
	                    query = "SELECT * FROM PRODUCTS WHERE ID=?";
	                    pst = this.con.prepareStatement(query);
	                    pst.setInt(1, item.getId());
	                    rs = pst.executeQuery();
	                    
	                    while (rs.next()) {
	                    	
	                    	//create new row in cart for each product that added to cart
	                        Cart row = new Cart();
	                        
	                        row.setId(rs.getInt("id"));
	                        row.setName(rs.getString("name"));
	                        row.setAuthor(rs.getString("author"));
	                        row.setCategory(rs.getString("category"));
	                        row.setPrice(rs.getDouble("price")*item.getQuantity());
	                        row.setQuantity(item.getQuantity());
	                        
	                        book.add(row);
	                    }
	                }
	            }	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
		 return book;		
	}
}
