package Backend;

public class Driver {

	public static void main(String[] args) {

		String status = "empty";
		Login logger = new Login();
		CustomerFunctions func = new CustomerFunctions();
		String username = "\"Nada\"";
		String res = logger.signin(username);
		if (res.compareTo("empty") != 0) {
			

			if (logger.signin_validation(res, "123456")) {

				status = logger.user_type("\"Nada\"");
			} else {

			}

		} else {
			

			// Here send data to be created

			// here enter value to be changed and it's type
			// Gui makes drop down list

			//logger.account_update("1111111111", "Password");

		}
		// Here User can use Functionalities according to his previlage
		// In case user is customer
		if (status.compareTo("Customer") == 0) {
			//Choose it from Gui
			String attribute = null;

		}

		else if (status.compareTo("Manager") == 0) {
			
		}



	}

}
