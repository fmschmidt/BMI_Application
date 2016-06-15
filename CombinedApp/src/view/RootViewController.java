package view;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Person;
import presenter.Validation;
import application.Main;
import controller.OverviewActionController;

public class RootViewController {

	// Reference to the main application.
	private Main mainApp;

	private LocalDate localDateAdd;

	private Validation validation;

	private OverviewActionController personOverviewController;

	private Person person;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personDataToRetrieve = FXCollections.observableArrayList();



	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 * @param personOvCont 
	 */
	public void setMainApp(Main mainApp, OverviewActionController personOvCont) {
		this.mainApp = mainApp;

	}



	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Felipe Schmidt\nBachelorarbeit\nHeadless Testing for Standalone User Interfaces\n\nView Object Pattern");

		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}


}
