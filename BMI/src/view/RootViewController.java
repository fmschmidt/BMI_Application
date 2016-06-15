package view;

import java.io.File;
import java.time.LocalDate;

import model.Person;
import presenter.MenuOptions;
import presenter.Validation;
import application.Main;
import controller.PersonOverviewActionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootViewController {

	// Reference to the main application.
	private Main mainApp;

	private LocalDate localDateAdd;

	private Validation validation;

	private PersonOverviewActionController personOverviewController;

	private Person person;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personDataToRetrieve = FXCollections.observableArrayList();


	private MenuOptions menuOptions;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 * @param personOvCont 
	 */
	public void setMainApp(Main mainApp, PersonOverviewActionController personOvCont) {
		this.mainApp = mainApp;

	}



	/**
	 * Creates an empty address book.
	 */
	@FXML
	private void handleNew() {
		personOverviewController = new PersonOverviewActionController();
		personOverviewController.getPersonData().clear();
		mainApp.setPersonFilePath(null);
		
	}

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	@FXML
	private void handleOpen() {
		
		menuOptions = new MenuOptions();
		menuOptions.openFile(mainApp);
		
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 */
	@FXML
	private void handleSave() {
		menuOptions = new MenuOptions();
		menuOptions.saveFile(mainApp);
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveAs() {
		menuOptions = new MenuOptions();
		menuOptions.saveFileAs(mainApp);
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		menuOptions = new MenuOptions();
		menuOptions.aboutMenu();
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}


}
