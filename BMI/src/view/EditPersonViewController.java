package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;
import presenter.Validation;
import controller.EditPersonViewActionController;


/**
 * Dialog to edit details of a person.
 * 
 * @author Felipe Schmidt
 */
public class EditPersonViewController {

	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtHeight;
	@FXML
	private TextField txtWeight;

	private Stage dialogStage;

	private Person person;

	private boolean okClicked = false;

	private Validation validation;

	private EditPersonViewActionController editPersonVC;

	private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		editPersonVC = new EditPersonViewActionController();
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
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 * @param dataToRetrieve 
	 */
	public void setPerson(Person person, ObservableList<Person> dataToRetrieve) {
		this.person = person;
		this.personData = dataToRetrieve;

		txtFirstName.setText(person.getFirstName());
		txtLastName.setText(person.getLastName());
		txtWeight.setText(Float.toString(person.getWeight()));
		txtHeight.setText(Float.toString(person.getHeight()));
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOkButton(){

		Person oldPerson = new Person();
		oldPerson = this.person;

		validation = new Validation();

		if(validation.editValidationOk(dialogStage, txtFirstName, txtLastName, txtWeight, txtHeight)){

			Person tempPerson = new Person(txtFirstName.getText().toString(), txtLastName.getText().toString(), 
					Float.parseFloat(txtWeight.getText()), Float.parseFloat(txtHeight.getText()));

			editPersonVC.saveEditedPerson(oldPerson,tempPerson, personData);

			dialogStage.close();
		}

	}

	@FXML
	private void handleCancelButton(){
		dialogStage.close();
	}

}
