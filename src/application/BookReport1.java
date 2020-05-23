package application;

public class BookReport1 {
	String title;
	String copies;
	String isbn;
	String times;
	public BookReport1(String title, String copies, String isbn, String times) {
		this.title = title;
		this.copies = copies;
		this.isbn = isbn;
		this.times = times;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCopies() {
		return copies;
	}
	public void setCopies(String copies) {
		this.copies = copies;
	}
	
}