package cucumber.tests;

import org.eclipse.jdt.internal.compiler.lookup.UpdatedMethodBinding;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.beans.binding.NumberExpressionBase;
import javafx.collections.ObservableList;
import model.Person;
import viewobjects.OverviewApplicationWindow;
import viewobjects.PersonInfoWindow;
import viewobjects.TableWindow;

public class StepDefinitions {

	private OverviewApplicationWindow ovAppWindow;
	private PersonInfoWindow piw;
	private TableWindow tw;
	private Person personDataToAdd;
	private Person personToBeEdited;
	private ObservableList<Person> personDataToRetrieve;


	@Given("^I have log entries$")
	public void I_have_log_entries() {

		ovAppWindow = new OverviewApplicationWindow();
		piw = new PersonInfoWindow();
		tw = new TableWindow();

		//personToBeEdited = new Person();

	}

	@When("^I enter a person information correctly:$")
	public void I_enter_a_person_information_correctly(DataTable arg1)  {
	
		//There are 2 approaches of adding a new person
		//1) add a new person by creating a new instance of a person and calling the method
		//   fillInInfo to add it
		//2) by "filling out" the text fields calling the windows as done below and creating a new instance
		//   of the person within the window class.
		
		/*
 		personDataToAdd = new Person();
		personDataToAdd.setFirstName("felzzz");
		personDataToAdd.setLastName("sch");
		personDataToAdd.setHeight(1.95f);
		personDataToAdd.setWeight(90.0f);
		 */

		//fills out the fields on the Window
		piw.setTxtName("xxxxx");
		piw.setTxtLastName("yyyyyy");

		piw.setTxtWeight("20");
		piw.setTxtHeight("2.10");

		//creates a new person and add
		tw = piw.fillInInfo(piw.createNewPersonToAdd());
	}

	@Then("^I see an updated table$")
	public void I_see_an_updated_table()  {
		tw.updatedTable();							//in case of inserting a new person within the table
		//ovAppWindow.getCombinedApp(piw, tw);		//in case of editing a new person we want to see the result of this table
	}

	@Then("^I have a new entry$")
	public void I_have_a_new_entry(){

		//shows the whole view
		ovAppWindow.getCombinedApp(piw, tw);
	}


	@When("^I enter a person information incorrectly:$")
	public void I_enter_a_person_information_incorrectly(DataTable arg1) throws NullPointerException {
		/*
		try{
			personDataToAdd = new Person();
			personDataToAdd.setFirstName("felzzz");
			personDataToAdd.setLastName("sch");
			personDataToAdd.setHeight(Float.parseFloat("abc"));
			personDataToAdd.setWeight(90.0f);
		}catch(NumberFormatException e){

		}
		 */

		piw.setTxtName("xxxxx");
		piw.setTxtLastName("yyyyyy");

		piw.setTxtWeight("abc");
		piw.setTxtHeight("1.98");

		tw = piw.fillInInfo(piw.createNewPersonToAdd());

	}

	@Then("^I see an error$")
	public void I_see_an_error(){
		System.out.println("\n\n >>>> ERRORRRRRR <<<< INVALID FIELDS \n");


		tw.updatedTable();


		System.out.println(">> Table shown above WITHOUT ANY MODIFICATION -- just to proove the error \n");
	}

	@Then("^I dont have a new entry$")
	public void I_dont_have_a_new_entry() {

		System.out.println(">> NO NEW ENTRY -- COMBINED APP << Showing current UI with the informations tried before \n");


		ovAppWindow.getCombinedApp(piw, tw);
	}

	@When("^I select a person to edit$")
	public void I_select_a_person_to_edit() {

		piw = tw.editPerson(3);

	}

	@When("^I edit the person informations correctly:$")
	public void I_edit_the_person_informations_correctly(DataTable arg1) {
		//when you edit some fields on the details
		piw.setTxtName("xxxxx");
		piw.setTxtLastName("yyyyyy");

		piw.setTxtHeight("1.98");
		piw.setTxtWeight("90");



		tw = tw.editPersonDetails(tw.getOldPerson(), piw.getNewPersonToEdit(tw.getOldPerson()));
	}

	@Then("^I have an edited entry$")
	public void I_have_an_edited_entry() {

		tw.assertTableCount(4);
	}

	@When("^I edit the person informations incorrectly:$")
	public void I_edit_the_person_informations_incorrectly(DataTable arg1) {

		piw.setTxtName("xxxxx");
		piw.setTxtLastName("yyyyyy");

		piw.setTxtWeight("abc");
		piw.setTxtHeight("2.40");

		tw = tw.editPersonDetails(tw.getOldPerson(), piw.getNewPersonToEdit(tw.getOldPerson()));
	}


}
