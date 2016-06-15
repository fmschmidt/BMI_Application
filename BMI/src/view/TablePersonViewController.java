package view;

import java.time.LocalDate;

import application.Main;
import controller.PersonOverviewActionController;
import controller.TablePersonActionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Person;
import presenter.MenuOptions;
import presenter.Validation;

public class TablePersonViewController {
	@FXML
	private TableView<Person> overallTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	@FXML
	private TableColumn<Person, Float> height;
	@FXML
	private TableColumn<Person, Float> weight;
	@FXML
	private TableColumn<Person, Float> bmiResult;
	@FXML
	private TableColumn<Person, LocalDate> date;

	private Stage dialogStage;

	private boolean okClicked = false;

	private LocalDate localDateAdd;

	private Main mainApp;

	private PersonOverviewActionController personOverviewController;

	private TablePersonViewController tablePersonVPresContr;

	private Person person;

	private TablePersonActionController tablePersonActionController;

	private Validation validation;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	private ObservableList<Person> dataToRetrieve = FXCollections.observableArrayList();

	MenuOptions menuOptions;

	public TablePersonViewController() {
		//personData.add(new Person("Felipe", "Schmidt"));
		//personData.add(new Person("Felipe", "de Medeiros"));
		//personData.add(new Person("de Medeiros", "Schmidt"));
		//personData.add(new Person("Schmidt", "Felipe"));
	}



	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the columns.

		//"cellData" is an arbitrary variable name that is just used inside that lambda expression.

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		weight.setCellValueFactory(new PropertyValueFactory<Person, Float>("weight"));
		height.setCellValueFactory(new PropertyValueFactory<Person, Float>("height"));
		bmiResult.setCellValueFactory(new PropertyValueFactory<Person, Float>("imcResult"));
		date.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("date"));
	}

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 * @param personOvCont
	 */
	public void setMainApp(Main mainApp, TablePersonActionController tablePersonContr) {
		this.mainApp = mainApp;

		//to update the table when some data is added on the table.
		personData = tablePersonContr.getPersonData();
		// Add observable list data to the table
		overallTable.setItems(tablePersonContr.getPersonData());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Delete person button.
	 */
	@FXML
	private void handleButtonDelete() {
		tablePersonActionController = new TablePersonActionController();
		validation = new Validation();

		dataToRetrieve = overallTable.getItems();

		int selectedIndex = overallTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			tablePersonActionController.deletePersonFromTheTable(dataToRetrieve, selectedIndex);
		} else {
			validation.deletePersonAlert(mainApp);
		}

	}

	/**
	 * Edit person button.
	 */
	@FXML
	private void handleButtonEdit() {

		//personOverviewController = new PersonOverviewActionController();
		validation = new Validation();
		tablePersonActionController = new TablePersonActionController();


		dataToRetrieve = overallTable.getItems();

		Person selectedPerson = overallTable.getSelectionModel().getSelectedItem();

		tablePersonActionController.editPersonFromTheTable(selectedPerson, dataToRetrieve);

		validation.showSelectedPerson(mainApp, selectedPerson, dataToRetrieve);

	}
	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 *
	 * @param person the person or null
	 */
	public void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			firstNameColumn.setText(person.getFirstName());
			lastNameColumn.setText(person.getLastName());
			weight.setText(Float.toString(person.getWeight()));
			height.setText(Float.toString(person.getHeight()));
		} else {
			// Person is null, remove all the text.
			firstNameColumn.setText("");
			lastNameColumn.setText("");
			weight.setText("");
			height.setText("");
		}
	}


}
