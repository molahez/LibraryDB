package Interfaces;

import java.util.ArrayList;

public interface Ilogin {
	
	public String signin(String email);
	
	public boolean signin_validation(String sys_pass, String entered_pass);

	public boolean signup(ArrayList<String> data);
	
	public boolean account_update(ArrayList<String> data);
	public ArrayList<String> account_data(String username);
	
	public String user_type(String username);
	

}
