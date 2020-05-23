package application;

public class CheckOutItem {
	private String title;
	private String isbn;
	private String copies;
	private String price;
	private String subprice;
	
	public CheckOutItem(String title, String isbn, String copies, String price, String subprice) {
		this.title = title;
		this.isbn = isbn;
		this.copies = copies;
		this.price = price;
		this.subprice = subprice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public  String getCopies() {
		return copies;
	}

	public  void setCopies(String copies) {
		this.copies = copies;
	}

	public  String getPrice() {
		return price;
	}

	public  void setPrice(String price) {
		this.price = price;
	}
	public  String getSubprice() {
		return subprice;
	}

	public  void setSubprice(String subPrice) {
		this.subprice = subPrice;
	}
}