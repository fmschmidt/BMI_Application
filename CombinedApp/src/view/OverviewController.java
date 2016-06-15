package view;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Person;
import presenter.Validation;
import application.Main;
import controller.OverviewActionController;

public class OverviewController {
//
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnSaveChanges;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtWeight;
	@FXML
	private TextField txtHeight;

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
	private TableColumn<Person, Float> imcResult;
	@FXML
	private TableColumn<Person, LocalDate> date;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;


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
	@FXML
	private Label lblInfoKg;
	@FXML
	private Label lblInfoMeters;
	@FXML
	private TextArea txtExplanation;


	// Reference to the main application.
	private Main mainApp;

	private LocalDate localDateAdd;

	private Validation validation;

	private OverviewActionController overviewActionController;

	private Person person; //used to set data on textFields

	private Person selectedPerson;

	private ObservableList<Person> personDataToRetrieve = FXCollections.observableArrayList();

	private ObservableList<Person> oldListForEditing = FXCollections.observableArrayList();

	//	private TablePersonViewPresenterController tablePersonVPresContr;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public OverviewController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.

		//"cellData" is an arbitrary variable name that is just used inside that lambda expression.

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		weight.setCellValueFactory(new PropertyValueFactory<Person, Float>("weight"));
		height.setCellValueFactory(new PropertyValueFactory<Person, Float>("height"));
		imcResult.setCellValueFactory(new PropertyValueFactory<Person, Float>("imcResult"));
		date.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("date"));

	}


	/**
	 * Sets the person to be edited in the dialog.
	 *
	 * @param person
	 * @param dataToRetrieve
	 */
	private void setPersonDataOnTextFields(Person person, ObservableList<Person> dataToRetrieve) {
		this.person = person;
		this.personDataToRetrieve = dataToRetrieve;

		txtName.setText(person.getFirstName());
		txtLastName.setText(person.getLastName());
		txtWeight.setText(Float.toString(person.getWeight()));
		txtHeight.setText(Float.toString(person.getHeight()));
	}

	private Person getPersonDataEditedOnTextFields(Person person) {
		person.setFirstName(txtName.getText().toString());
		person.setLastName(txtLastName.getText().toString());
		person.setWeight(Float.parseFloat(txtWeight.getText().toString()));
		person.setHeight(Float.parseFloat(txtHeight.getText().toString()));

		float fWeight;
		float fHeight;
		float fBMIResult;

		fWeight = Float.parseFloat(txtWeight.getText().toString());
		fHeight = Float.parseFloat(txtHeight.getText().toString());
		fBMIResult = (fWeight / (fHeight * fHeight));

		person.setImcResult(fBMIResult);
		person.setDate(localDateAdd.now());

		return person;
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 * @param personOvActCont
	 */
	public void setMainApp(Main mainApp, OverviewActionController personOvActCont) {
		this.mainApp = mainApp;

		//to update the table when some data is added on the table.
		personDataToRetrieve = personOvActCont.getPersonData();
		// Add observable list data to the table
		overallTable.setItems(personOvActCont.getPersonData());
	}

	/**
	 * Insert new person button.
	 */
	@FXML
	private void handleButtonConfirm() {
		overviewActionController = new OverviewActionController(personDataToRetrieve);
		validation = new Validation();

		boolean personAlreadyExists = false;

		personAlreadyExists = overviewActionController.checkExistingPerson(overallTable.getItems(), txtName.getText().toString(), txtLastName.getText().toString() );

		if(!personAlreadyExists){
			if (validation.inputValidationOk(mainApp, txtName, txtLastName, txtWeight, txtHeight))
			{
		        overviewActionController.addNewPersonData(txtName.getText(), txtLastName.getText(), txtWeight.getText(), txtHeight.getText());
			}
		}else{
			validation.personAlreadyExistsAlert(mainApp);
		}
	}


	/**
	 * Delete person button.
	 */
	@FXML
	private void handleButtonDelete() {
		overviewActionController = new OverviewActionController();
		validation = new Validation();

		personDataToRetrieve = overallTable.getItems();

		int selectedIndex = overallTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			overviewActionController.deletePersonFromTheTable(personDataToRetrieve, selectedIndex);
		} else {
			validation.deletePersonAlert(mainApp);
		}

	}

	/**
	 * Edit person button.
	 */
	@FXML
	private void handleButtonEdit() {

		overviewActionController = new OverviewActionController();
		validation = new Validation();

		personDataToRetrieve = overallTable.getItems();

		selectedPerson = overallTable.getSelectionModel().getSelectedItem();

		if (selectedPerson != null) {
			overviewActionController.editPersonFromTheTable(selectedPerson, personDataToRetrieve);

			setPersonDataOnTextFields(selectedPerson, oldListForEditing);
		} else {
			validation.deletePersonAlert(mainApp);
		}


	}

	@FXML
	private void handleButtonSaveChanges(){

		overviewActionController = new OverviewActionController();
		validation = new Validation();

		if (validation.inputValidationOk(mainApp, txtName, txtLastName, txtWeight, txtHeight))
		{
			personDataToRetrieve = overallTable.getItems();

			Person p = new Person();
			p = getPersonDataEditedOnTextFields(p);

			overviewActionController.editPersonFromTheTable(selectedPerson, personDataToRetrieve);

			overviewActionController.saveEditedPersonUI(selectedPerson, p, personDataToRetrieve);
		}

	}



}
