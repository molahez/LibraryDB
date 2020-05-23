package application;

import java.io.IOException;
import java.util.ArrayList;

import Backend.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignUpController {
	@FXML
	TextField username;
	@FXML
	TextField email;
	@FXML
	TextField firstname;
	@FXML
	TextField lastname;
	@FXML
	TextField addressname;
	@FXML
	TextField phone;
	@FXML
	PasswordField password;
	@FXML
	Label error;

	@FXML
	public void signUp(ActionEvent event) throws IOException {
		// add to the database
		// if kolo tamam {
		
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
			if(logger.signup(data)) {
				Stage primaryStage = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/application/CustomerHomePage.fxml"));
				Scene scene1 = new Scene(root,397 , 451);
				scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene1);
				Stage stage = (Stage) username.getScene().getWindow();
		    	stage.close();
				primaryStage.show();
			}
			else {
				
				username.setText("");
				password.setText("");
				firstname.setText("");
				lastname.setText("");
				email.setText("");
				phone.setText("");
				addressname.setText("");
				
			}
			
		 	
		// else { 
			//error.setVisible(true);
	}
		else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Error");
			errorAlert.setContentText("Fields are incompelte, Please fill it");
			errorAlert.showAndWait();
	
			}
		}
	@FXML
	void back(ActionEvent event) throws IOException {
		username.setText("");
		password.setText("");
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/First.fxml"));
		Scene scene1 = new Scene(root, 396, 405);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		Stage stage = (Stage) username.getScene().getWindow();
		stage.close();
		primaryStage.show();
	}
	
	

	
}
