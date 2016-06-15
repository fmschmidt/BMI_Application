package presenter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Main;

public class Validation {



	/**
	 * Validates the input of the user in the PersonOverviewController.
	 * @return true if the input is valid
	 */
	public boolean inputValidationOk(Main mainApp, TextField txtName, TextField txtLastName, TextField txtWeight, TextField txtHeight) {
		String errorMessage = "";

		errorMessage = fieldsValidation(errorMessage, txtName, txtLastName, txtWeight, txtHeight);

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Invalid Inputs");
			alert.setHeaderText("Please correct the following invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	/**
	 * Validates the input when the user finishes to edit some data.
	 * @return true if the input is valid
	 */
	public boolean editValidationOk(Stage dialogStage, TextField txtName, TextField txtLastName, TextField txtWeight, TextField txtHeight){

		String errorMessage = "";

		errorMessage = fieldsValidation(errorMessage, txtName, txtLastName, txtWeight, txtHeight);

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Inputs");
			alert.setHeaderText("Please correct the following invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	public void deletePersonAlert(Main mainApp){
		// Nothing selected.
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("No Selection");
		alert.setHeaderText("No Person Selected");
		alert.setContentText("Please select a person in the table.");

		alert.showAndWait();
	}
	
	
	public void personAlreadyExistsAlert(Main mainApp){
		// Nothing selected.
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("Person Already Exists");
		alert.setHeaderText("This person was already inserted within the table");
		alert.setContentText("Please change the Name or the Last Name.");

		alert.showAndWait();
	}
	
/*
	public boolean showSelectedPerson(Main mainApp, Person selectedPerson){
		if (selectedPerson != null) {

			boolean okClicked = mainApp.personEditDialog(selectedPerson);

			return okClicked;

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
			
			return false;
		}
	}
	*/

	/**
	 * Validates the input textfields of a user input.
	 * @return a string with the error details
	 */
	private String fieldsValidation(String errorMessage, TextField txtName, TextField txtLastName, TextField txtWeight, TextField txtHeight){
		if (txtName.getText() == null || txtName.getText().length() == 0) {
			errorMessage += "FIRST NAME not valid!\n"; 
		}

		if (txtLastName.getText() == null || txtLastName.getText().length() == 0){
			errorMessage += "LAST NAME not valid!\n"; 
		}

		if (txtWeight.getText() == null || txtWeight.getText().length() == 0) {
			errorMessage += "WEIGHT not valid!\n"; 
		} else {
			// try to parse the postal code into an int.
			try {
				Float.parseFloat(txtWeight.getText());
			} catch (NumberFormatException e) {
				errorMessage += "WEIGHT not valid (must be a float)!\n"; 
			}
		}

		if (txtHeight.getText() == null || txtHeight.getText().length() == 0) {
			errorMessage += "HEIGHT not valid!\n"; 
		} else {
			// try to parse the postal code into an int.
			try {
				Float.parseFloat(txtHeight.getText());
			} catch (NumberFormatException e) {
				errorMessage += "HEIGHT not valid (must be a float)!\n"; 
			}
		}

		return errorMessage;
	}

}
