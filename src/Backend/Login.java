package Backend;

import Interfaces.Ilogin;
import application.Main;

import java.sql.*;
import java.util.ArrayList;

public class Login implements Ilogin {

	private String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/bookstore";
	private String User = "SAMPLE";
	private String Password = "12345678zZ";
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst = null;

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
	public String signin(String username) {
		String res = null;

		DB_connection();
		String sql = "SELECT username,password FROM bookstore.users where username=" + "\"" + username + "\"";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {

				String columnValue = rs.getString(2);

				res = columnValue;
				System.out.println("");
			} else {
				System.out.println("Username doesn't exist");
				System.out.println("If you are not registered ,go back and sign up");
				res = "empty";

			}
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return res;
	}

	@Override
	public boolean signin_validation(String sys_pass, String entered_pass) {
		boolean flag = false;

		if (sys_pass.compareTo(entered_pass) == 0) {
			System.out.println("Sign in Successfull ");
			flag = true;
		} else {
			System.out.println("Incorrect Password, Please try it again ");
			flag = false;
		}
		return flag;

	}

	@Override
	public String user_type(String username) {
		String status = null;
		DB_connection();

		// Here we will retrieve state of user
		String sql = "SELECT status FROM bookstore.users where username=" + "\"" + username + "\"";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {

				status = rs.getString(1);

			} else {

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean signup(ArrayList<String> data) {
		Boolean flag = false;
		DB_connection();

		// Here we will insert New User date which we take from GUI
		String sql = ("INSERT INTO users (username, password,lastName,FirstName,email,phoneNumber,shippingAddress,status) "
				+ "VALUES (?, ?,?,?, ?,?,?, ?)");

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, data.get(0));
			pst.setString(2, data.get(1));
			pst.setString(3, data.get(2));
			pst.setString(4, data.get(3));
			pst.setString(5, data.get(4));
			pst.setString(6, data.get(5));
			pst.setString(7, data.get(6));
			pst.setString(8, "customer");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pst.executeUpdate();
			con.close();
			System.out.println("You have succeccfully signed up");
			flag = true;
		} catch (SQLException ex) {
			System.out.println("Insertion Failed Username already exists:\n" + ex.toString());
			ex.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean account_update(ArrayList<String> data) {
		boolean flag = false;
		DB_connection();

		// Here we will data of current user
		String sql = ("update users set " + "username = '" + data.get(0) + "'" + ",password = '" + data.get(1) + "'"
				+ ",lastName = '" + data.get(3) + "'" + ",firstName = '" + data.get(2) + "'" + ",email = '"
				+ data.get(4) + "'" + ",phoneNumber = '" + data.get(5) + "'" + ",shippingAddress = '" + data.get(6)
				+ "'" + "where username=" + "\"" + Main.getusername() + "\"");
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

	@Override
	public ArrayList<String> account_data(String username) {
		ArrayList<String> data = new ArrayList<String>();
		DB_connection();
		String sql = "SELECT username, password,lastName,FirstName,email,phoneNumber,shippingAddress,status FROM bookstore.users where username="
				+ "\"" + username + "\"";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				for (int i = 1; i <= 8; i++) {
					data.add(rs.getString(i));
				}

			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(data.size());
		return data;
	}

}
