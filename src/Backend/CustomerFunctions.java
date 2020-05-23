package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Interfaces.ICustomerFunctions;
import application.Main;

public class CustomerFunctions implements ICustomerFunctions {

	private String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/bookstore";
	private String User = "SAMPLE";
	private String Password = "12345678zZ";
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData md;
	PreparedStatement pst = null;

	private void DB_connection() {

		try {
			Class.forName(MYSQL_DRIVER);
			System.out.println("Class Loaded....");
			con = DriverManager.getConnection(MYSQL_URL, User, Password);
			System.out.println("Connected to the database....");
			st = con.createStatement();
			// here we select user matching username in case no match we promot him to sign
			// up

		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException:\n" + ex.toString());
			ex.printStackTrace();

		} catch (SQLException ex) {
			System.out.println("SQLException:\n" + ex.toString());
			ex.printStackTrace();
		}

	}


	@Override
	public boolean Cart_additem(String ISBN, String No_of_copies) {
		Boolean flag = false;
		String price = null;
		int Toprice;
		String Tprice;
		DB_connection();
		// select book price from db
		String q1 = "SELECT SellingPrice FROM bookstore.book where ISBNnumber=" + "\"" + ISBN + "\"";
		try {
			pst = con.prepareStatement(q1);
			rs = pst.executeQuery();
			if (rs.next()) {

				price = rs.getString(1);

			} else {

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Toprice = Integer.parseInt(price) * Integer.parseInt(No_of_copies);
		Tprice = Integer.toString(Toprice);
		// Here we will insert New item in cart

		String q2 = ("INSERT INTO cart (ISBN, price,copies,totalPrice) " + "VALUES (?, ?,?,?)");

		try {
			pst = con.prepareStatement(q2);
			pst.setString(1, ISBN);
			pst.setString(2, price);
			pst.setString(3, No_of_copies);
			pst.setString(4, Tprice);
			pst.executeUpdate();
			con.close();
			System.out.println("Item has been added ");
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;

	}

	@Override
	public ArrayList<ArrayList<String>> Cart_view() {
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		DB_connection();
		String q1 = "SELECT ISBN,Price, t1.copies,totalPrice,Title\r\n" + "  FROM cart t1\r\n"
				+ "  INNER JOIN book t2 \r\n" + "    ON t1.ISBN = t2.ISBNnumber";
		try {
			pst = con.prepareStatement(q1);
			rs = pst.executeQuery();
			md = (ResultSetMetaData) rs.getMetaData();
			int cols = md.getColumnCount();
			while (rs.next()) {
				ArrayList<String> temp = new ArrayList<String>();

				for (int i = 1; i <= cols; i++) {
					temp.add(rs.getString(i));
				}

				data.add(temp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public void Cart_removeitem(String ISBN) {
		DB_connection();
		String q1 = "delete from cart where ISBN = ?";
		try {
			pst = con.prepareStatement(q1);
			pst.setString(1, ISBN);
			pst.execute();
			System.out.println("Item has been deleted succeccfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ArrayList<String>> book_search(String value, String attribute) {
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		DB_connection();
		String q1 = "SELECT ISBNnumber,Title,Author,PublisherName ,YEAR(STR_TO_DATE(PublicationYear, \"%Y/%m/%d\")),SellingPrice,Category,Copies,threshold FROM bookstore.book where "
				+ attribute + "= \"" + value + "\"";
		try {
			pst = con.prepareStatement(q1);
			rs = pst.executeQuery();
			md = (ResultSetMetaData) rs.getMetaData();
			int cols = md.getColumnCount();
			while (rs.next()) {
				ArrayList<String> temp = new ArrayList<String>();

				for (int i = 1; i <= cols; i++) {

					temp.add(rs.getString(i));
				}

				data.add(temp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void checkout_user(String username, String buyingDate, String amountPaid) {
		DB_connection();
		String q1 = ("INSERT INTO purchases (username, buyingDate,amountPaid) " + "VALUES (?, ?,?)");

		try {
			pst = con.prepareStatement(q1);
			pst.setString(1, username);
			pst.setString(2, buyingDate);
			pst.setString(3, amountPaid);
			pst.executeUpdate();
			String q2 = ("call bookstore.log_out()");
			pst = con.prepareStatement(q2);
			pst.executeUpdate();
			
			con.close();
			System.out.println("Purchase process has been saved to system records");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void checkout_book(String ISBN, String sellingDate, String soldCopies) {
		DB_connection();
		String old_copies;
		String new_copiess;
		int new_copies;
		String q1 = ("INSERT INTO soldbooks (ISBN, sellingDate,soldCopies) " + "VALUES (?, ?,?)");

		try {
			pst = con.prepareStatement(q1);
			pst.setString(1, ISBN);
			pst.setString(2, sellingDate);
			pst.setString(3, soldCopies);

			pst.executeUpdate();
			con.close();
			System.out.println("Purchase process has been saved to system records ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// update book and remove it from records
		DB_connection();

		String q2 = ("SELECT Copies  From bookstore.book where " + "ISBNnumber = \"" + ISBN + "\"");
		try {
			pst = con.prepareStatement(q2);
			rs = pst.executeQuery();
			if (rs.next()) {

				old_copies = rs.getString(1);
				new_copies = Integer.parseInt(old_copies) - Integer.parseInt(soldCopies);
				new_copiess = Integer.toString(new_copies);
				String q3 = ("update book set Copies = '" + new_copiess + "'" + "where ISBNnumber =" + "\"" + ISBN + "\"");
				pst = con.prepareStatement(q3);
				pst.executeUpdate(q3);
				
				con.close();
				System.out.println("Purchase process has been been removed from stock ");


			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		

	}

	@Override
	public void logout() {
		DB_connection();

		String q2 = ("call bookstore.log_out()");
		try {
			pst = con.prepareStatement(q2);
			pst.executeUpdate();
			con.close();
			System.out.println("Cart has beed discarded succeccfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
