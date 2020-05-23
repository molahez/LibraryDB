package Interfaces;

import java.util.ArrayList;

public interface IMangerFunctions {
	public boolean add_book(ArrayList<String> book);
	public boolean user_promot (String username);
	public boolean book_update(ArrayList<String> book , String old_ISBN);
	
}
