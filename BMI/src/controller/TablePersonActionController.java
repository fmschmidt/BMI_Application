package controller;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

public class TablePersonActionController {

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	private LocalDate localDateAdd;

	private EditPersonViewActionController editPersonActionController;

	/**
	 * Constructor
	 */
	public TablePersonActionController() {
		personData.add(new Person("Felipe", "Schmidt"));
		personData.add(new Person("Felipe", "de Medeiros"));
		personData.add(new Person("de Medeiros", "Schmidt"));
		personData.add(new Person("Schmidt", "Felipe"));
	}

	/**
	 * Constructor to retrieve some personData already created on the table, and not to lose it in future.
	 */
	public TablePersonActionController(ObservableList<Person> personData) {
		this.personData = personData;
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}



	public void deletePersonFromTheTable(ObservableList<Person> dataToRetrieve, int selectedIndex){
		dataToRetrieve.remove(selectedIndex);
	}

	public void editPersonFromTheTable(Person selectedPerson, ObservableList<Person> dataToRetrieve){
		editPersonActionController = new EditPersonViewActionController();
		editPersonActionController.editSelectedPerson(selectedPerson, dataToRetrieve);
	}

	public void addNewPersonData(String txtName, String txtLastName, String txtWeight, String txtHeight) {

		float fIMCResult;
		float fWeight;
		float fHeight;
		fWeight = Float.parseFloat(txtWeight);
		fHeight = Float.parseFloat(txtHeight);

		fIMCResult = (fWeight / (fHeight * fHeight));

		Person tempPerson = new Person(txtName, txtLastName,
				fWeight, fHeight,
				fIMCResult, localDateAdd.now());

		addNewPerson(tempPerson);
	}

	public void addNewPerson(Person person){
		if(!person.getFirstName().isEmpty() && !person.getLastName().isEmpty()){

			float fIMCResult;
			float fWeight;
			float fHeight;
			fWeight = person.getWeight();
			fHeight = person.getHeight();

			fIMCResult = (fWeight / (fHeight * fHeight));

			person.setWeight(fWeight);
			person.setHeight(fHeight);
			person.setImcResult(fIMCResult);

			getPersonData().add(person);
		}
	}

}
