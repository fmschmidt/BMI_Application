package controller;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Person;

public class OverviewActionController {

	private LocalDate localDateAdd;

	private int index;
	
	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public OverviewActionController() {
		personData.add(new Person("Felipe", "Schmidt"));
		personData.add(new Person("Felipe", "de Medeiros"));
		personData.add(new Person("de Medeiros", "Schmidt"));
		personData.add(new Person("Schmidt", "Felipe"));
	}

	/**
	 * Constructor to retrieve some personData already created on the table, and not to lose it in future.
	 */
	public OverviewActionController(ObservableList<Person> personData) {

		this.personData = personData;
	}

	/**
	 * Returns the data as an observable list of Persons. 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

/**
 * return true if the person exists
 * @param personList
 * @param name
 * @param lastName
 * @return
 */
	public boolean checkExistingPerson(ObservableList<Person> personList, String name, String lastName){
		
		for(Person personWithinTable: personList){
            if (personWithinTable.getFirstName().toString().toLowerCase().equals(name.toLowerCase())
            		&&
            	personWithinTable.getLastName().toString().toLowerCase().equals(lastName.toLowerCase()) )
            {
            	return true;
            }
        }
		return false;
		
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
		//System.out.println("PEOPLE: " + getPersonData().toString());
	}

	public void deletePersonFromTheTable(ObservableList<Person> dataToRetrieve, int selectedIndex){
		dataToRetrieve.remove(selectedIndex);
	}

	public void selectPersonToEditFromTheTable(TableView<Person> overallTable){
		//TODO: button edit?
		//Here I thought that I could call the method "showPersonDetails" in the PresenterController class.
		//but have to consider that if I call this method here, I would be calling the UI.
	}

	public void editPersonFromTheTable(Person selectedPerson, ObservableList<Person> dataToRetrieve) {
		float fWeight;
		float fHeight;
		float fBMIResult;

		fWeight = selectedPerson.getWeight();
		fHeight = selectedPerson.getHeight();
		fBMIResult = (fWeight / (fHeight * fHeight));

		selectedPerson.setFirstName(selectedPerson.getFirstName());
		selectedPerson.setLastName(selectedPerson.getLastName());
		selectedPerson.setHeight(fHeight);
		selectedPerson.setWeight(fWeight);
		selectedPerson.setImcResult(fBMIResult);
		//selectedPerson.setDate(localDateAdd.now());
	}

	
	/*
	 * why 2 saveEditions? 1 for the UI, one for the test
	 * Because of the ObservableList.
	 * As shown below, I cant simple get the DB and change the index there.
	 * The javaFX simply doesnt refresh the tableView in real time.
	 * It makes me clear the table and add it everything again.
	 * But when I do that in headless testing (if I try to clear and addAll elements again) it shows me a table containing 0 elements.
	 * Then I searched about the Clear() method and I got an answer for that:
	 * "Removes all of the elements from this list (optional operation). The list will be empty after this call returns."
	 * It means, it Clears the ObservableList and add all the elements passed as var-args.
	 * So when I try to pass the "PersonList" again by arguments, it's not possible, because the collection "Person" was already deleted.
	 * 
	 * Because of that, I decided to make 2 differents Save Editions, one that the UI handles and the other the tests.
	 * 
	 * It has 2 different behaviours.
	 * 1) set a breakpoint at the line personList.set(index, personEdited);
	 * 		then run main in debug mode and try to edit a person
	 * 2) set a breakpoint at the same line, and change the TableWindow method to use this method saveEditedPersonUI as well
	 * 		then run the Test in debug mode.
	 * 
	 * There are 2 behaviors for the same method.
	 * The variables act differently.
	 */
	
	public void saveEditedPersonUI(Person oldPerson, Person personEdited, ObservableList<Person> personList){
		//gets the index of the previous person
		index = personList.lastIndexOf(oldPerson);
		
		personList.set(index, personEdited);
		
		//updates the global list.
		getPersonData().clear();
		getPersonData().addAll(personList);
	}
	
	public void saveEditedPerson(Person oldPerson, Person personEdited, ObservableList<Person> personList) {
		index = personList.lastIndexOf(oldPerson);
		
		getPersonData().set(index, personEdited);
		
	}

}
