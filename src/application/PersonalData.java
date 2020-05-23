package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Backend.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PersonalData {

    @FXML
    private TextField firstname;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField addressname;

    @FXML
    private TextField email;

    @FXML
    private TextField username = new TextField();
    
    @FXML
    private TextField lastname;

    @FXML
    void savePersonalInfo(ActionEvent event) throws IOException {
    	
		if(username.getText().compareTo("") != 0 && password.getText().compareTo("") != 0 &&firstname.getText().compareTo("") != 0 &&lastname.getText().compareTo("") != 0 &&email.getText().compareTo("") != 0&&
		phone.getText().compareTo("") != 0&&addressname.getText().compareTo("") != 0) {
		ArrayList<String> data = new ArrayList<String>();
			
			data.add(username.getText());
			data.add(password.getText());
			data.add(firstname.getText());
			data.add(lastname.getText());
			data.add(email.getText());
			data.add(phone.getText());
			data.add(addressname.getText());
			Login logger = new Login();
			if(logger.account_update(data)) {
				Main.setusername(username.getText());
				Stage stage = (Stage) username.getScene().getWindow();
		    	stage.close();
			
			}
			else {
				
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Invalid");
				errorAlert.setContentText("Username already exists");
				errorAlert.showAndWait();
				
				ArrayList<String> account_data =logger.account_data(Main.getusername());
				username.setText(account_data.get(0));
				password.setText(account_data.get(1));
				phone.setText(account_data.get(2));
				addressname.setText(account_data.get(3));
				email.setText(account_data.get(4));
				lastname.setText(account_data.get(5));
				firstname.setText(account_data.get(6));
				
			}

    }}
    
	public void initialize() {
		
		Login logger = new Login();
		ArrayList<String> account_data =logger.account_data(Main.getusername());
		username.setText(account_data.get(0));
		password.setText(account_data.get(1));
		phone.setText(account_data.get(2));
		addressname.setText(account_data.get(3));
		email.setText(account_data.get(4));
		lastname.setText(account_data.get(5));
		firstname.setText(account_data.get(6));
	}

}
