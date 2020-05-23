package application;


public class Book {
	private String isbn;
	private String copies;
	private String title;
    private String category;
    private String author;
    private String price;
    private String year;
    private String publisher;
    private String threshold;
    
    public Book(String isbn, String title, String category, String author, String price, String year, String publisher, String copies, String threshold) {
    	this.title = title;
    	this.price = price;
    	this.publisher = publisher;
    	this.category = category;
    	this.year = year;
    	this.author = author;
    	this.isbn = isbn;
    	this.copies = copies;
    	this.threshold = threshold;
    }

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	

	public String getthreshold() {
		return threshold;
	}

	public void setthreshold(String threshold) {
		this.threshold = threshold;
	}
}
