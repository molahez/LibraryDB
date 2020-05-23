package application;

public class UserReport {
	String fname;
	String lname;
	String price;
	String number;
	
	UserReport(String fname, String lname, String price, String number){
		this.fname = fname;
		this.lname = lname;
		this.price = price;
		this.number = number;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}