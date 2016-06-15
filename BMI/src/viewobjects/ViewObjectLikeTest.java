package viewobjects;

import java.util.Random;

import model.Person;

import org.junit.Before;
import org.junit.Test;

import controller.TablePersonActionController;

public class ViewObjectLikeTest {

	private InsertDataWindow idw;
	private TableResultsWindow trw;
	private EditPersonWindow epw;
	private Person personDataToAdd;
	private Person personToBeEdited;

	@Before
	public void setUpTests(){
		idw = new InsertDataWindow();
		trw = new TableResultsWindow();
		//epw = new EditPersonWindow();

		personDataToAdd = new Person();
		personDataToAdd.setFirstName("felzzz");
		personDataToAdd.setLastName("sch");
		personDataToAdd.setHeight(1.95f);
		personDataToAdd.setWeight(90.0f);

		personToBeEdited = new Person();
	}

	//@Test
	public void testAddNewPersonInfo(){

		idw = idw.fillInInfo(personDataToAdd);
		trw = idw.AddPerson().assertTableCount(5);

	}

	//@Test
	public void testShowTable(){

		trw = idw.showTableResults();

	}

	@Test
	public void testDeletePerson(){
		trw = idw.showTableResults();
		trw.removePerson(2);		//random index for a person to be deleted from the table.
		trw.updatedTable();			//shows the table updated after the action
		trw.assertTableCount(3);	//checks if the table is right
	}

	//@Test
	public void testEditPerson(){
	//	idw.open().showTableResults();
		trw = idw.showTableResults();
		trw.editPerson(2);				//index of the person to be edited

		//gets the person using the index and stores in a local variable here within this class.
		personToBeEdited = trw.personToEdit;

		//edits the values on this variable here inside.
		personToBeEdited.setFirstName("xxxxx");
		personToBeEdited.setLastName("yyyyy");
		personToBeEdited.setHeight(1.95f);
		personToBeEdited.setWeight(30.0f);

		epw = new EditPersonWindow(personToBeEdited);
		//sends the values to the window of edition
		//it's on this call that all the values and data are changed/edited for the new ones.
		epw.editPersonDetails(trw.personToEdit, personToBeEdited);

		trw.updatedTable();
		//.assertPerson(0, personToBeEdited);			//TODO: check if it's really updating on the table (getPersonData).
	}

}
