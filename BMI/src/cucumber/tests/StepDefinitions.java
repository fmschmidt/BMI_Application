package cucumber.tests;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;
import viewobjects.EditPersonWindow;
import viewobjects.InsertDataWindow;
import viewobjects.TableResultsWindow;


public class StepDefinitions {

	private InsertDataWindow idw;
	private TableResultsWindow trw;
	private EditPersonWindow epw;
	private Person personDataToAdd;
	private Person personToBeEdited;
	private ObservableList<Person> personDataList;
	private List<Person> personListInitial;


	@Given("^I have log entries:$")
	public void I_have_log_entries(List<Person> personList) {
		personDataList = FXCollections.observableList(personList);
		idw = new InsertDataWindow();
		trw = new TableResultsWindow();
	}

	/*@When("^I enter a person information correctly:$")
	public void I_enter_a_person_information_correctly(List<Person> newEntryList) {

	   personDataToAdd = new Person(newEntryList.get(0).getFirstName(),
			   newEntryList.get(0).getLastName(), newEntryList.get(0).getWeight(), newEntryList.get(0).getHeight());

	   idw = idw.fillInInfo(personDataToAdd);
	   trw = idw.AddPerson();
	}*/

	@When("^I enter a person information:$")
	public void I_enter_a_person_information(List<Person> newEntryList)  {
		personDataToAdd = new Person(newEntryList.get(0).getFirstName(),
				   newEntryList.get(0).getLastName(), newEntryList.get(0).getWeight(), newEntryList.get(0).getHeight());

		  idw = idw.fillInInfo(personDataToAdd);
		  trw = idw.AddPerson();
	}

	@Then("^I see a result table:$")
	public void I_see_a_result_table(List<Person> newEntryList)  {
		trw = trw.updatedTable().assertTableCount(newEntryList.size());
	}


/*	@When("^I enter a person information incorrectly:$")
	public void I_enter_a_person_information_incorrectly(List<Person> newEntryList)  {
		personDataToAdd = new Person(newEntryList.get(0).getFirstName(),
				   newEntryList.get(0).getLastName(), newEntryList.get(0).getWeight(), newEntryList.get(0).getHeight());

		idw = idw.fillInInfo(personDataToAdd);
		trw = idw.AddPerson();
	}*/

	@When("^I remove the person on the line (\\d+)$")
	public void I_remove_the_person_line(int arg1)  {
		trw = idw.showTableResults();
		trw = trw.removePerson(arg1);
	}

}
