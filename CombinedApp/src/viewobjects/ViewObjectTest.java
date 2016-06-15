package viewobjects;

import javafx.collections.ObservableList;
import model.Person;

import org.junit.Before;
import org.junit.Test;

public class ViewObjectTest {

	private OverviewApplicationWindow ovAppWindow;
	private PersonInfoWindow piw;
	private TableWindow tw;
	private Person personDataToAdd;
	private Person personToBeEdited;
	private ObservableList<Person> personDataToRetrieve;

	@Before
	public void setUpTests(){
		ovAppWindow = new OverviewApplicationWindow();
		piw = new PersonInfoWindow();
		tw = new TableWindow();

		personDataToAdd = new Person();
		personDataToAdd.setFirstName("felzzz");
		personDataToAdd.setLastName("sch");
		personDataToAdd.setHeight(1.95f);
		personDataToAdd.setWeight(90.0f);

		personToBeEdited = new Person();
	}

	@Test
	public void testAddNewPersonInfo(){
		idw.open().fillInInfo(personDataToAdd);

		tw = piw.fillInInfo(personDataToAdd);

		ovAppWindow.getCombinedApp(piw, tw);

		tw.assertTableCount(5);

		ovAppWindow = ovAppWindow.fillInInfoAndAdd(personDataToAdd);

	}


	@Test
	public void testDeletePerson(){
		idw.open().showTableResults();
		trw.removePerson(2);		//random index for a person to be deleted from the table.
		trw.updatedTable();			//shows the table updated after the action
		trw.assertTableCount(3);	//checks if the table is right

		ovAppWindow = ovAppWindow.deletePerson(2);

		ovAppWindow = ovAppWindow.deletePerson(2);

	}

	@Test
	public void testEditPerson(){
		idw.open().showTableResults();

		tw.updatedTable();

		//when a person on the table is selected and you press EDIT
		piw = tw.editPerson(3);				//index of the person to be edited

		//when you edit some fields on the details
		piw.setTxtName("xxxx");
		piw.setTxtLastName("dasdas");

		piw.setTxtHeight("abc");
		piw.setTxtWeight("900");
		//when you SAVE CHANGES
		tw = tw.editPersonDetails(tw.getOldPerson(), piw.getNewPersonToEdit(tw.getOldPerson()));


		ovAppWindow.getCombinedApp(piw, tw);

		tw.assertTableCount(4);
	}

	@Test
	public void testEditPersonnew(){
		//when you edit some fields on the details
		tw.updatedTable();
		ovAppWindow = ovAppWindow.editPerson(3);

		piw.setTxtName("xxxx");
		piw.setTxtLastName("dasdas");

		piw.setTxtHeight("abc");
		piw.setTxtWeight("900");

		ovAppWindow = ovAppWindow.editPersonDetails(tw.getOldPerson(), piw.getNewPersonToEdit(tw.getOldPerson()));

	}


}
