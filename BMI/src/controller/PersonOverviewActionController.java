package controller;

import java.time.LocalDate;

import view.PersonOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Person;
import application.Main;

public class PersonOverviewActionController {

	private LocalDate localDateAdd;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public PersonOverviewActionController() {

		//personData.add(new Person("Felipe", "Schmidt"));
		//personData.add(new Person("Felipe", "de Medeiros"));
		//personData.add(new Person("de Medeiros", "Schmidt"));
		//personData.add(new Person("Schmidt", "Felipe"));
	}

	/**
	 * Constructor to retrieve some personData already created on the table, and not to lose it in future.
	 */
	public PersonOverviewActionController(ObservableList<Person> personData) {

		//this.personData = personData;
	}

	/**
	 * Returns the data as an observable list of Persons. 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

	/*
	public void addNewPerson(TextField txtName, TextField txtLastName, TextField txtWeight, TextField txtHeight){

		float fWeight;
		float fHeight;
		float fIMCResult;

		fWeight = Float.parseFloat(txtWeight.getText());
		fHeight = Float.parseFloat(txtHeight.getText());


		fIMCResult = (fWeight / (fHeight * fHeight));

		Person tempPerson = new Person(txtName.getText(), txtLastName.getText(), 
				fWeight, fHeight,
				fIMCResult, localDateAdd.now());

		getPersonData().add(tempPerson);
	}

	public void deletePersonFromTheTable(TableView<Person> overallTable, int selectedIndex){

		//TODO: 	try..catch? or just remove?
		overallTable.getItems().remove(selectedIndex);

	}

	public void selectPersonToEditFromTheTable(TableView<Person> overallTable){
		//TODO: button edit?
		//Here I thought that I could call the method "showPersonDetails" in the PresenterController class.
		//but have to consider that if I call this method here, I would be calling the UI.
	}
	 */

}
