package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Person;
import presenter.Validation;
import application.Main;
import controller.PersonOverviewActionController;
import controller.TablePersonActionController;

public class PersonOverviewController {
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtWeight;
	@FXML
	private TextField txtHeight;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnShowTable;
	@FXML
	private Label lblName;
	@FXML
	private Label lblLastName;
	@FXML
	private Label lblWeight;
	@FXML
	private Label lblHeight;
	@FXML
	private Label lblMessageStatus;

	private TablePersonActionController tablePersonActionController;

	private Validation validation;
	// Reference to the main application.
	private Main mainApp;
	//The data as an observable list of Persons.
	private ObservableList<Person> personDataToRetrieve = FXCollections.observableArrayList();


	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public PersonOverviewController() {
		tablePersonActionController = new TablePersonActionController();
		personDataToRetrieve = tablePersonActionController.getPersonData();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

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
	 * Insert new person button. 
	 */
	@FXML
	public void handleButtonConfirm() {
		tablePersonActionController = new TablePersonActionController(personDataToRetrieve);
		validation = new Validation();

		if (validation.inputValidationOk(mainApp, txtName, txtLastName, txtWeight, txtHeight))
		{
			tablePersonActionController.addNewPersonData(txtName.getText(), txtLastName.getText(), txtWeight.getText(), txtHeight.getText());
			validation.showTablePerson(mainApp, tablePersonActionController);
		}
	}

	/**
	 * Insert new person button. 
	 */
	@FXML
	public void handleButtonShowTable() {
		tablePersonActionController = new TablePersonActionController(personDataToRetrieve);
		validation = new Validation();

		validation.showTablePerson(mainApp, tablePersonActionController);
	}
}
