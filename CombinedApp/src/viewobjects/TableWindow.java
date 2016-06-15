package viewobjects;

import org.junit.Assert;

import controller.OverviewActionController;
import javafx.collections.ObservableList;
import model.Person;

public class TableWindow {


	private ObservableList<Person> personDataToRetrieve;

	private String BTN_DELETE = "BTN_DELETE";

	private String BTN_EDIT = "BTN_EDIT";

	private static String BTN_SAVE_CHANGES = "BTN_SAVE_CHANGES";

	private OverviewActionController overviewController;

	private int personIndex;

	private Person personToEdit;

	private Person oldPerson;

	private Person personToBeEdited;

	private PersonInfoWindow piw;


	public TableWindow(){

		overviewController = new OverviewActionController();

		personToEdit = new Person();
		oldPerson = new Person();
		personToBeEdited = new Person();
		personToBeEdited = personToEdit;

		personDataToRetrieve = overviewController.getPersonData();

		//piw = new PersonInfoWindow();
	}


	/**
	 * Constructor with a Controller as an argument
	 * @param tablePersonController
	 */
	public TableWindow(OverviewActionController oc){
		//printTableConsole(overviewController);

		overviewController = oc;
		setTableWindow(overviewController.getPersonData());
	}

	public void setOldPerson(Person person){
		this.oldPerson = person;
	}
	public Person getOldPerson(){
		return oldPerson;
	}

	public TableWindow assertPerson(int index, Person person){
		overviewController = new OverviewActionController();
		overviewController.getPersonData().get(index);

		Assert.assertEquals(person.getFirstName(), overviewController.getPersonData().get(index).getFirstName());

		return this;
	}

	public TableWindow assertTableCount(int count){

		int personListSize = overviewController.getPersonData().size();

		Assert.assertNotNull(overviewController.getPersonData());
		org.testng.Assert.assertEquals(personListSize, count);

		System.out.println(">> assertTableCount: OK!");

		return this;
	}

	public TableWindow updatedTable(){
		printTableConsole(overviewController);

		return this;
	}

	public TableWindow removePerson(int index){
		//TODO
		//should it be >>> removePerson(Person p, int index)? (int index as a parameter)
		//How to check the index (the person) that we want to delete by a click?
		//is it necessary? or can we just simulate about the index?
		//I think we can just pretend we are deleting with the index.

		select(index);
		click(BTN_DELETE);
		return this;
	}

	public PersonInfoWindow editPerson(int index){
		select(index);
		click(BTN_EDIT);

		setOldPerson(personToEdit);

		return new PersonInfoWindow(personToEdit);
	}

	public TableWindow editPersonDetails(Person oldPersonSelected, Person newPerson){
		oldPerson = oldPersonSelected;
		personToBeEdited = newPerson;

		//piw.setFields(personToBeEdited);

		click(BTN_SAVE_CHANGES);

		//piw.clearFields();

		return new TableWindow(overviewController);
	}

	//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------


		/**
		 * This method just takes the index of a person to be deleted within the table.
		 * @param index
		 */
		private void select(int index){
			personIndex = index;
		}


		/**
		 * It gets the button of the UI to be clicked and do that.
		 * @param element
		 */
		public void click(String element){

			if(element == BTN_DELETE){
				//TODO ???
				//remove element from the table
				//note: the argument is TableView.. I have to change it within the Controller class
				//to be able to remove a person without taking into account the view FX

				//tpc.deletePersonFromTheTable(overallTable, selectedIndex);

				overviewController.deletePersonFromTheTable(overviewController.getPersonData(), personIndex);
			}

			if(element == BTN_EDIT){
				//a new dialog to edit a person opens
				personToEdit = overviewController.getPersonData().get(personIndex);
				overviewController.editPersonFromTheTable(personToEdit, overviewController.getPersonData());
			}

			if(element == BTN_SAVE_CHANGES){
				if(!overviewController.checkExistingPerson(overviewController.getPersonData(), personToBeEdited.getFirstName().toString(), personToBeEdited.getLastName().toString())){
					overviewController.editPersonFromTheTable(oldPerson, personDataToRetrieve);
					overviewController.saveEditedPerson(oldPerson, personToBeEdited, overviewController.getPersonData());
				}
			}


		}


		/**
		 * Prints the table on the console to see the results
		 * @param tablePersonController
		 */
		public void printTableConsole(OverviewActionController overviewController){
			personDataToRetrieve = overviewController.getPersonData();

			System.out.println("| Name | \t | Last Name | \t | Height | \t | Weight | \t | BMI | \t");
			for (Person p : personDataToRetrieve){
				System.out.println("| " + p.getFirstName() + "\t\t" + p.getLastName() + "\t\t"
						+ p.getHeight() + "\t\t" + p.getWeight() + "\t\t" + p.getImcResult() + " |");
			}
			System.out.println("\n\n");
		}


		//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------



	public void setTableWindow(ObservableList<Person> pdtr){
		this.personDataToRetrieve = pdtr;
		//System.out.println("aAEWWW dentro: " + personDataToRetrieve.size());
	}

	public TableWindow getTableWindow(){

		System.out.println("| Name | \t | Last Name | \t | Height | \t | Weight | \t | BMI | \t");
		for (Person p : personDataToRetrieve){
			System.out.println("| " + p.getFirstName() + "\t\t" + p.getLastName() + "\t\t"
					+ p.getHeight() + "\t\t" + p.getWeight() + "\t\t" + p.getImcResult() + " |");
		}
		System.out.println("\n\n");

		return new TableWindow(overviewController);
	}



}
