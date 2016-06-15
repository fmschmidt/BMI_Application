package controller;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import model.Person;

public class EditPersonViewActionController {

	private LocalDate localDateAdd;

	private TablePersonActionController tpc;
	
	private int index;
	
	
	/**
	 * Constructor
	 */
	public EditPersonViewActionController() {
		tpc = new TablePersonActionController();
	}
	
	public void editSelectedPerson(Person person, ObservableList<Person> dataToRetrieve){
		
		float fWeight;
		float fHeight;
		float fBMIResult;

		fWeight = person.getWeight();
		fHeight = person.getHeight();
		fBMIResult = (fWeight / (fHeight * fHeight));

		person.setFirstName(person.getFirstName());
		person.setLastName(person.getLastName());
		person.setHeight(fHeight);
		person.setWeight(fWeight);
		person.setImcResult(fBMIResult);
		person.setDate(localDateAdd.now());
		
	}
	
	public void saveEditedPerson(Person oldPerson, Person personEdited, ObservableList<Person> personList){
		//gets the index of the previous person
		index = personList.lastIndexOf(oldPerson);
		
		//changes the new person with the old person within the list.
		personList.set(index, personEdited);
		
		//updates the global list.
		tpc.getPersonData().clear();
		tpc.getPersonData().addAll(personList);
/*	
		System.out.println("\n ");
		for (Person tempPerson : tpc.getPersonData())
			System.out.println("tempPerson: " + tempPerson.getFirstName() + "\t" + tempPerson.getLastName() + "\t" + tempPerson.getHeight()+"\t"+ tempPerson.getWeight());
*/
	}
	
}
