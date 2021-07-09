package bookshop.nurozkaya.model;


//Cart that extends from Products and has a quantity variable
//When u add books to cart you can increase or decrease quantity of products 
//I can access all the methods from Product model 
public class Cart extends Product{
	
	private int quantity;

	public Cart() {
	
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	

}
