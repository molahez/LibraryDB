package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FisrtController {
	@FXML
	Button button;
	@FXML
	public void signIn(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/SignInCustomer.fxml"));
		Scene scene1 = new Scene(root, 382, 450);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		Stage stage = (Stage) button.getScene().getWindow();
	    stage.close();
		primaryStage.show();
	}
	@FXML
	public void signUp(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/Signup.fxml"));
		Scene scene1 = new Scene(root,340, 432);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		Stage stage = (Stage) button.getScene().getWindow();
	    stage.close();
		primaryStage.show();
	}
}
