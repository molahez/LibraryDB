package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Backend.ManagerFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditBookController implements Initializable{
	private Book book;
	String isbn;
	@FXML
	TextField isbnField;
	@FXML
	TextField titleField;
	@FXML
	TextField aothorField;
	@FXML
	TextField priceField;
	@FXML
	TextField copiesField;
	@FXML
	TextField publisherField;
	@FXML
	TextField categoryField;
	@FXML
	TextField yearField;
	@FXML
	TextField thresholdField;
	@FXML
	public void save(ActionEvent event) throws IOException {
		
		/*isbnField.setText(book.getIsbn());
		titleField.setText(book.getTitle());
		aothorField.setText(book.getAuthor());
		priceField.setText(book.getPrice());
		copiesField.setText(book.getCopies());
		publisherField.setText(book.getPublisher());
		categoryField.setText(book.getCategory());
		yearField.setText(book.getYear());*/
		
		
		//update book from fields
		
		
		ArrayList<String> book = new ArrayList<String>();
		book.add(isbnField.getText());
		book.add(titleField.getText());
		book.add(aothorField.getText());
		book.add(publisherField.getText());
		book.add(yearField.getText());
		book.add(priceField.getText());
		book.add(categoryField.getText());
		book.add(copiesField.getText());
		book.add(thresholdField.getText());
		
		ManagerFunctions mf = new ManagerFunctions();
		mf.book_update(book, isbn);
		
		Stage stage = (Stage) isbnField.getScene().getWindow();
	    stage.close();
	    
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		book = Search.getBook();
		isbnField.setText(book.getIsbn());
		titleField.setText(book.getTitle());
		aothorField.setText(book.getAuthor());
		priceField.setText(book.getPrice());
		copiesField.setText(book.getCopies());
		publisherField.setText(book.getPublisher());
		categoryField.setText(book.getCategory());
		yearField.setText(book.getYear());
		thresholdField.setText(book.getthreshold());
		isbn = book.getIsbn();
	}
}