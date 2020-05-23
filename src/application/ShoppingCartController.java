package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Backend.CustomerFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShoppingCartController implements Initializable{

	private int itemIndex = 0;
	private final ObservableList<CheckOutItem> data = FXCollections.observableArrayList();
	private ArrayList<ArrayList<String>> output;
	private int totalInt = 0;
	
	@FXML
	TableView<CheckOutItem> table = new TableView<CheckOutItem>();
	@FXML
	TableColumn<CheckOutItem, String> title = new TableColumn<CheckOutItem, String>("title");
	@FXML
	TableColumn<CheckOutItem, String> price = new TableColumn<CheckOutItem, String>("price");

	@FXML
	TableColumn<CheckOutItem, String> copies = new TableColumn<CheckOutItem, String>("copies");
    @FXML
    TableColumn<CheckOutItem, String> subprice = new TableColumn<CheckOutItem, String>("subprice");
	@FXML
	TableColumn<CheckOutItem, String> isbn = new TableColumn<CheckOutItem, String>("isbn");
	@FXML
	Button remove = new Button();
	@FXML
	TextField total = new TextField();
	@FXML
	Button checkout;
	
	 @FXML
	 Button Refresh = new Button();
	
	@FXML
	public void removeItem(ActionEvent event) throws IOException {
		Refresh.setVisible(true);
		String isbnNumber = output.get(itemIndex).get(0);
	
		CustomerFunctions csfnc = new CustomerFunctions();
		csfnc.Cart_removeitem(isbnNumber);
		data.remove(itemIndex);

		 // remove from shopping cart then refresh scene
	
		Stage stage = (Stage) remove.getScene().getWindow();
	    stage.close();
	    
	    
	    

	}
    @FXML
    void Refresh(ActionEvent event) {
 
	    table.getItems().clear();
	    table.setItems(data);
	    table.refresh();

	    
    }

	
	@FXML
	public void checkOut(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/CreditCard.fxml"));
		Scene scene1 = new Scene(root,331, 327);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Refresh.setVisible(false);
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		subprice.setCellValueFactory(new PropertyValueFactory<>("subprice"));
		copies.setCellValueFactory(new PropertyValueFactory<>("copies"));
		isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		// add everything in the cart to the table
		CustomerFunctions csfnc = new CustomerFunctions();
		totalInt = 0;

		output = csfnc.Cart_view();  ///// <-------------------------- change with the cart 2d array
		totalInt = cart_total(output);
		table.setItems(data);
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        itemIndex = data.indexOf(table.getSelectionModel().getSelectedItem());
		        
		     // use the index to remove from the cart
		        
		        Stage primaryStage = new Stage();
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/application/RemoveItem.fxml"));
					Scene scene1 = new Scene(root,378, 303);
					scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene1);
					primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   	total.setEditable(true);
				totalInt = totalInt -(Integer.parseInt(output.get(itemIndex).get(3)));
				total.setText(String.valueOf(totalInt));
				total.setEditable(false);
				Refresh.setVisible(true);
				
		        
		    }
		});
		
		total.setText(String.valueOf(totalInt));
		total.setEditable(false);
	}
	public int cart_total(ArrayList<ArrayList<String>> outpu) {
		for(int i = 0; i < outpu.size(); i++) {
			ArrayList<String> temp = outpu.get(i);
			CheckOutItem b = new CheckOutItem(temp.get(4), temp.get(0), temp.get(2), temp.get(1), temp.get(3));
			data.add(b);
			totalInt += (Integer.parseInt(temp.get(3)));
		}
		return totalInt;
		
	}
	

	
	

}