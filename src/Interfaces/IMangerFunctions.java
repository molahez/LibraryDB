package Interfaces;

import java.util.ArrayList;

public interface IMangerFunctions {
	public boolean add_book(ArrayList<String> book);
	public boolean user_promot (String username);
	public boolean book_update(ArrayList<String> book , String old_ISBN);
	public boolean add_order(String isbn, String quantity);
	public boolean confirm_order(String isbn);
	public ArrayList<ArrayList<String>> Total_sales();
	public ArrayList<ArrayList<String>> best_sales();
	public ArrayList<ArrayList<String>> top_customers();
}
