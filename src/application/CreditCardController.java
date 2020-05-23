package application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import Backend.CustomerFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CreditCardController {
	@FXML
	Button checkout;
	@FXML
	TextField number = new TextField();
	@FXML
	TextField date = new TextField();
	@FXML
	Label error;
	
	private ArrayList<ArrayList<String>> output;

	@FXML
	public void checkOut(ActionEvent event) throws IOException {
		String card_regex = "\\d{4}-?\\d{4}-?\\d{4}-?\\d{4}";
		String date_regex = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";

		if (number.getText().matches(card_regex) && date.getText().matches(date_regex)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			CustomerFunctions csfnc = new CustomerFunctions();
			ShoppingCartController total = new ShoppingCartController();
			output = csfnc.Cart_view();
			for(int i = 0; i < output.size(); i++) {
				ArrayList<String> temp = output.get(i);
				csfnc.checkout_book(temp.get(0), dtf.format(now), temp.get(2));

			}
			System.out.println(Main.getusername());
			csfnc.checkout_user(Main.getusername(), dtf.format(now), Integer.toString(total.cart_total(output)));
			//Delete all cart
			

		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Invalid");
			errorAlert.setContentText("Incorrect Card Number or expiry date");
			errorAlert.showAndWait();

			System.out.println("Invalid");
		}

		/*
		 * if(/*valid card) {
		 * 
		 * //remove all elements from cart //sell books from db
		 * 
		 * Stage stage = (Stage) checkout.getScene().getWindow(); stage.close();
		 * 
		 * //refresh cart /*} else { error.setVisible(true); }
		 */
	}
}
