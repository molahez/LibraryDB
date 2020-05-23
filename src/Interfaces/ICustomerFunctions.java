package Interfaces;

import java.util.ArrayList;

public interface ICustomerFunctions {

	
	public boolean Cart_additem(String ISBN, String No_of_copies);
	public ArrayList<ArrayList<String>>  Cart_view(); //Just select The whole table
	public void Cart_removeitem(String ISBN);
	public void checkout_user(String username, String buyingDate, String amountPaid);
	public void checkout_book(String ISBN, String sellingDate,String SoldCopies);
	public ArrayList<ArrayList<String>>  book_search(String value,String attribute);
	public void logout();
}
