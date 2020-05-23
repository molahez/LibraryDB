package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Interfaces.IMangerFunctions;
import application.Main;

public class ManagerFunctions implements IMangerFunctions {
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
	public boolean add_book(ArrayList<String> book) {
		Boolean flag = false;
		DB_connection();
		// Here we will insert New book data from Manager
		String sql = ("INSERT INTO book (ISBNnumber, Title,Author,PublisherName,PublicationYear,SellingPrice,Category,Copies,threshold) "
				+ "VALUES (?, ?,?,?, ?,?,?, ?,?)");

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, book.get(0));
			pst.setString(2, book.get(1));
			pst.setString(3, book.get(2));
			pst.setString(4, book.get(3));
			pst.setString(5, book.get(4));
			pst.setString(6, book.get(5));
			pst.setString(7, book.get(6));
			pst.setString(8, book.get(7));
			pst.setString(9, book.get(8));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pst.executeUpdate();
			con.close();
			System.out.println("Book has succeccfully been inserted");
			flag = true;
		} catch (SQLException ex) {
			System.out.println("Insertion Failed as book already exits\n" + ex.toString());
			ex.printStackTrace();
		}

		return flag;

	}

	@Override
	public boolean user_promot(String username) {
		DB_connection();

		// Here we will retrieve state of user
		String sql = ("update users set status = \"manager\" where username =" + "\"" + username + "\"");
		try {
			pst = con.prepareStatement(sql);
			pst.executeUpdate(sql);
			con.close();
			System.out.println("User has been promoted to manager");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean book_update(ArrayList<String> book , String old_ISBN) {
		boolean flag = false;
		DB_connection();

		// Here we will update date of current book
		String sql = ("update book set " + "ISBNnumber = '" + book.get(0) + "'" + ",Title = '" + book.get(1) + "'"
				+ ",Author = '" + book.get(2) + "'" + ",PublisherName = '" + book.get(3) + "'" + ",PublicationYear = '"
				+ book.get(4) + "'" + ",SellingPrice = '" + book.get(5) + "'" + ",Category = '" + book.get(6) + "'"
				+ ",Copies = '" + book.get(7) + "'" + ",threshold = '" + book.get(8) + "'" + "where ISBNnumber=" + "\""
				+ old_ISBN + "\"");
		try {
			pst = con.prepareStatement(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pst.executeUpdate();
			con.close();
			flag = true;
			System.out.println("You have succeccfully changed data");
		} catch (SQLException ex) {
			System.out.println("Update Failed Username already exists:\n" + ex.toString());
			ex.printStackTrace();
		}

		return flag;
	}

}
