package application;

import java.io.IOException;
import java.util.ArrayList;

import Backend.CustomerFunctions;
import Backend.Login;
import Backend.ManagerFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

public class ManagerHomePageController {
	static boolean manager = false;

	public static boolean getManager() {
		return manager;
	}

	public static void setManager(boolean manager) {
		Search.manager = manager;
	}

	@FXML
	Button add;
	@FXML
	TextField username = new TextField();
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
	Button signout;

	@FXML
	public void personalInfo(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/PersonalInfo.fxml"));
		Scene scene1 = new Scene(root, 327, 394);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		primaryStage.show();

		// get user info from db and put then in the text fields

	}

	@FXML
	public void savePersonalInfo(ActionEvent event) throws IOException {
		// update user db from text fields
		Stage stage = (Stage) username.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void searchForBooks(ActionEvent event) throws IOException {
		setManager(true);
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/SearchForBooks.fxml"));
		Scene scene1 = new Scene(root, 821, 566);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	@FXML
	public void shoppingCart(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/ShoppingCart.fxml"));
		Scene scene1 = new Scene(root, 559, 539);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	@FXML
	public void logout(ActionEvent event) throws IOException {

		// delete all shopping cart
		CustomerFunctions csfnc = new CustomerFunctions();
		csfnc.logout();
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/First.fxml"));
		Scene scene1 = new Scene(root, 396, 405);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage) signout.getScene().getWindow();
		stage.close();
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	@FXML
	TextField number;
	@FXML
	TextField quantity;

	@FXML
	public void addBook(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/AddBook.fxml"));
		Scene scene1 = new Scene(root, 382, 450);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	@FXML
	public void placeOrder(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/PlaceOrder.fxml"));
		Scene scene1 = new Scene(root, 331, 316);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		primaryStage.show();

	}

	@FXML
	public void place(ActionEvent event) throws IOException {
		if (number.getText().compareTo("") != 0 && quantity.getText().compareTo("") != 0) {

			ManagerFunctions mf = new ManagerFunctions();

			if (mf.add_order(number.getText(), quantity.getText())) {
				Stage stage = (Stage) number.getScene().getWindow();
				stage.close();
			} else {

				number.setText("");
				quantity.setText("");

			}
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Error");
			errorAlert.setContentText("Fields are incompelte, Please fill it");
			errorAlert.showAndWait();

		}

	}

	@FXML
	TextField num;

	@FXML
	public void confirmOrder(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/ConfirmOrder.fxml"));
		Scene scene1 = new Scene(root, 327, 254);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	@FXML
	public void confirm(ActionEvent event) throws IOException {
		if (num.getText().compareTo("") != 0) {

			ManagerFunctions mf = new ManagerFunctions();

			if (mf.confirm_order(num.getText())) {

				Stage stage = (Stage) num.getScene().getWindow();
				stage.close();
			} else {

				num.setText("");

			}
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Error");
			errorAlert.setContentText("Fields are incompelte, Please fill it");
			errorAlert.showAndWait();

		}

	}

	@FXML
	TextField name;

	@FXML
	public void promoteUser(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/application/PromoteUser.fxml"));
		Scene scene1 = new Scene(root, 285, 265);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene1);
		primaryStage.show();

	}

	@FXML
	public void promote(ActionEvent event) throws IOException {
		if (name.getText().compareTo("") != 0) {
			Login logger = new Login();
			String res = logger.signin(name.getText());
			if (res.compareTo("empty") != 0) {
				String status = "empty";
				status = logger.user_type(name.getText());
				if (status.compareTo("manager") == 0) {
					Alert errorAlert = new Alert(AlertType.INFORMATION);
					errorAlert.setHeaderText("Nothing is to be done here user is already a manager");
					errorAlert.showAndWait();
				} else if (status.compareTo("customer") == 0) {
					ManagerFunctions mngrfnc = new ManagerFunctions();
					mngrfnc.user_promot(name.getText());

				}
				// update user status to manager

				Stage stage = (Stage) name.getScene().getWindow();
				stage.close();
			} else {
				name.setText("");
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Username doesn't exist to upgrade it");
				errorAlert.showAndWait();
			}

		} else {
			name.setText("");
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Error");
			errorAlert.setContentText("Field is incompelte, Please fill it");
			errorAlert.showAndWait();
		}

	}

	@FXML
	public void viewReports(ActionEvent event) throws IOException {
		ManagerFunctions mngrfnc = new ManagerFunctions();

		ArrayList<ArrayList<String>> report1 = mngrfnc.Total_sales();// add array 1 here
		ArrayList<ArrayList<String>> report2 = mngrfnc.top_customers();// add array 2 here
		ArrayList<ArrayList<String>> report3 = mngrfnc.best_sales();// add array 3 here
		
JasperReports jasper = new JasperReports();
		
		ArrayList<BookReport1> list1 = new ArrayList<BookReport1>();
		for(int i = 0; i < report1.size(); i++) {
			BookReport1 b1 = new BookReport1(report1.get(i).get(1), report1.get(i).get(2), report1.get(i).get(0), report1.get(i).get(3));
			list1.add(b1);
		}
		try {
			jasper.totalSales(list1);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<UserReport> list2 = new ArrayList<UserReport>();
		for(int i = 0; i < report2.size(); i++) {
			UserReport b1 = new UserReport(report2.get(i).get(0), report2.get(i).get(1), report2.get(i).get(2), report2.get(i).get(3));
			list2.add(b1);
		}
		try {
			jasper.topPurchasing(list2);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<BookReport1> list3 = new ArrayList<BookReport1>();
		for(int i = 0; i < report3.size(); i++) {
			BookReport1 b1 = new BookReport1(report3.get(i).get(1), report3.get(i).get(2), report3.get(i).get(0), report3.get(i).get(3));
			list3.add(b1);
		}
		try {
			jasper.topTen(list3);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
