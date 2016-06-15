package viewobjects;

import org.junit.Assert;

import controller.EditPersonViewActionController;
import controller.TablePersonActionController;
import javafx.collections.ObservableList;
import model.Person;

public class TableResultsWindow {

	public ObservableList<Person> personDataToRetrieve;

	private String BTN_DELETE = "BTN_DELETE";

	private String BTN_EDIT = "BTN_EDIT";

	private TablePersonActionController tpac;

	private EditPersonViewActionController editPersonActionController;

	private int personIndex;

	public Person personToEdit;

	/**
	 * Constructor
	 */
	public TableResultsWindow(){
		tpac = new TablePersonActionController(personDataToRetrieve);
		tpac.getPersonData();

		//initializes the editPersonActionController if necessary to edit an entry
		//editPersonActionController = new EditPersonViewActionController();

		//initializes a person if necessary to edit.
		personToEdit = new Person();
	}

	/**
	 * Constructor with a Controller as an argument
	 * @param tablePersonActionController
	 */
	public TableResultsWindow(TablePersonActionController tablePersonActionController){
		//updates the variable associated to the TablePersonActionController from this class
		tpac = tablePersonActionController;
		printTableConsole(tablePersonActionController);
	}

	/**
	 * Constructor with a Controller as an argument
	 * @param tablePersonController
	 */
	public TableResultsWindow(ObservableList<Person> listPerson){
		printTableConsoleShowTableBtn(listPerson);
	}


	public TableResultsWindow assertPerson(int index, Person person){
		tpac = new TablePersonActionController();
		tpac.getPersonData().get(index);

		Assert.assertEquals(person.getFirstName(), tpac.getPersonData().get(index).getFirstName());

		return this;
	}

	public TableResultsWindow assertTableCount(int count){

		int personListSize = tpac.getPersonData().size();

		Assert.assertNotNull(tpac.getPersonData());
		org.testng.Assert.assertEquals(personListSize, count);

		System.out.println(">> assertTableCount: OK!");

		return this;
	}

	public TableResultsWindow updatedTable(){
		printTableConsole(tpac);

		return this;
	}

	public TableResultsWindow removePerson(int index){
		//TODO
		//should it be >>> removePerson(Person p, int index)? (int index as a parameter)
		//How to check the index (the person) that we want to delete by a click?
		//is it necessary? or can we just simulate about the index?
		//I think we can just pretend we are deleting with the index.

		select(index);
		click(BTN_DELETE);
		return this;
	}

	public EditPersonWindow editPerson(int index){
		select(index);
		click(BTN_EDIT);

		return new EditPersonWindow(personToEdit);
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
	private void click(String element){

		if(element == BTN_DELETE){
			//TODO ???
			//remove element from the table
			//note: the argument is TableView.. I have to change it within the Controller class
			//to be able to remove a person without taking into account the view FX

			//tpc.deletePersonFromTheTable(overallTable, selectedIndex);

			tpac.deletePersonFromTheTable(tpac.getPersonData(), personIndex);
		}

		if(element == BTN_EDIT){
			//a new dialog to edit a person opens
			personToEdit = tpac.getPersonData().get(personIndex);
			tpac.editPersonFromTheTable(personToEdit, tpac.getPersonData());
		}


	}


	/**
	 * Prints the table on the console to see the results
	 * @param tablePersonController
	 */
	public void printTableConsole(TablePersonActionController tablePersonController){
		personDataToRetrieve = tablePersonController.getPersonData();

		System.out.println("| Name | \t | Last Name | \t | Height | \t | Weight | \t | BMI | \t");
		for (Person p : personDataToRetrieve){
			System.out.println("| " + p.getFirstName() + "\t\t" + p.getLastName() + "\t\t"
					+ p.getHeight() + "\t\t" + p.getWeight() + "\t\t" + p.getImcResult() + " |");
		}
		System.out.println("\n\n");
	}


	public void printTableConsoleShowTableBtn(ObservableList<Person> listPerson){
		System.out.println("| Name | \t | Last Name | \t | Height | \t | Weight | \t | BMI | \t");
		for (Person p : listPerson){
			System.out.println("| " + p.getFirstName() + "\t\t" + p.getLastName() + "\t\t"
					+ p.getHeight() + "\t\t" + p.getWeight() + "\t\t" + p.getImcResult() + " |");
		}
		System.out.println("\n\n");
	}

	//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------


}
