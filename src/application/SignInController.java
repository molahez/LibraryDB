package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Backend.Login;;

public class SignInController implements Initializable {
	@FXML
	TextField username = new TextField();

	@FXML
	PasswordField password = new PasswordField();
	@FXML
	ComboBox<String> userType;

	@FXML
	public void signIn(ActionEvent event) throws IOException {
		String status = "empty";
		Login logger = new Login();
	
		if(username.getText().compareTo("") != 0 && password.getText().compareTo("") != 0) {
			String res = logger.signin(username.getText());
			Main.setusername(username.getText());
			if (res.compareTo("empty") != 0) {

				if (logger.signin_validation(res, password.getText())) {
					status = logger.user_type(username.getText());
					if (status.compareTo("customer") == 0) {
						// Choose it from Gui
						Stage primaryStage = new Stage();
						Parent root;
						root = FXMLLoader.load(getClass().getResource("/application/CustomerHomePage.fxml"));
						Scene scene1 = new Scene(root, 354, 516);
						scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene1);
						Stage stage = (Stage) username.getScene().getWindow();
						stage.close();
						primaryStage.show();

					}

					else if (status.compareTo("manager") == 0) {
						Stage primaryStage = new Stage();
						Parent root;
						root = FXMLLoader.load(getClass().getResource("/application/ManagerHomePage.fxml"));
						Scene scene1 = new Scene(root, 354, 516);
						scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene1);
						Stage stage = (Stage) username.getScene().getWindow();
						stage.close();
						primaryStage.show();
					}

					// status = logger.user_type("\"Nada\"");
				} else {
					username.setText("");
					password.setText("");
					/*
					 * Stage primaryStage = new Stage(); Parent root; root =
					 * FXMLLoader.load(getClass().getResource("/application/First.fxml")); Scene
					 * scene1 = new Scene(root, 354, 516);
					 * scene1.getStylesheets().add(getClass().getResource("application.css").
					 * toExternalForm()); primaryStage.setScene(scene1); Stage stage = (Stage)
					 * username.getScene().getWindow(); stage.close(); primaryStage.show();
					 */
				}

			} else {
				username.setText("");
				password.setText("");
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Username doesn't exist");
				errorAlert.setContentText("If you are not registered ,go back and sign up");
				errorAlert.showAndWait();


				// logger.account_update("1111111111", "Password");

			}
			
			
			
		}
		else {
			username.setText("");
			password.setText("");
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Error");
			errorAlert.setContentText("Fields are incompelte, Please fill it");
			errorAlert.showAndWait();
	

			
		}


		/* if(true && userType.getValue() == "Manager") { */

		/* } */

		/*
		 * if(true && userType.getValue() == "Customer") { Stage primaryStage = new
		 * Stage(); Parent root; root =
		 * FXMLLoader.load(getClass().getResource("/application/CustomerHomePage.fxml"))
		 * ; Scene scene1 = new Scene(root,397 , 451);
		 * scene1.getStylesheets().add(getClass().getResource("application.css").
		 * toExternalForm()); primaryStage.setScene(scene1); Stage stage = (Stage)
		 * username.getScene().getWindow(); stage.close(); primaryStage.show(); /*}
		 */
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}

}
