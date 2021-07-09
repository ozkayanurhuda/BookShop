package bookshop.nurozkaya.model;

//Book Products that has id, name, category, price, image

public class Product {
	private int id;
	private String name;
	private String author;
	private String category;
	private Double price;
	private String image;
	
	
	public Product() {
		
	}

	public Product(int id, String name, String author, String category, Double price, String image) {
		this.id = id;
		this.name = name;
		this.author=author;
		this.category = category;
		this.price = price;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", author=" + author + ", category=" + category + ", price="
				+ price + ", image=" + image + "]";
	}
}
