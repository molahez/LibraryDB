package application;

import java.io.IOException;
import java.util.ArrayList;

import Backend.ManagerFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BookAddController {
	@FXML
	TextField isbnField = new TextField();
	@FXML
	TextField titleField = new TextField();
	@FXML

	TextField authorField = new TextField();
	@FXML

	TextField priceField = new TextField();
	@FXML
	TextField copiesField = new TextField();
	@FXML
	TextField publisherField = new TextField();
	@FXML
	TextField categoryField = new TextField();
	@FXML
	TextField yearField = new TextField();
	@FXML
	TextField thresholdfield = new TextField();

	@FXML
	public void save(ActionEvent event) throws IOException {
		if (isbnField.getText().compareTo("") != 0 && titleField.getText().compareTo("") != 0
				&& authorField.getText().compareTo("") != 0 && priceField.getText().compareTo("") != 0
				&& copiesField.getText().compareTo("") != 0 && publisherField.getText().compareTo("") != 0
				&& categoryField.getText().compareTo("") != 0 && yearField.getText().compareTo("") != 0
				&& thresholdfield.getText().compareTo("") != 0) {
			ArrayList<String> book = new ArrayList<String>();
			book.add(isbnField.getText());
			book.add(titleField.getText());
			book.add(authorField.getText());
			book.add(publisherField.getText());
			book.add(yearField.getText());
			book.add(priceField.getText());
			book.add(categoryField.getText());
			book.add(copiesField.getText());
			book.add(thresholdfield.getText());

			ManagerFunctions mngrfnc = new ManagerFunctions();
			if (mngrfnc.add_book(book)) {
				Stage stage = (Stage) isbnField.getScene().getWindow();
		    	stage.close();
			} else {

				isbnField.setText("");
				titleField.setText("");
				authorField.setText("");
				publisherField.setText("");
				yearField.setText("");
				categoryField.setText("");
				priceField.setText("");
				copiesField.setText("");
				thresholdfield.setText("");

			}
		} else {

			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Error");
			errorAlert.setContentText("Fields are incompelte, Please fill it");
			errorAlert.showAndWait();

		}
	}
}
