package viewobjects;

import controller.TablePersonActionController;
import javafx.collections.ObservableList;
import model.Person;

/**
 * Class related to the TablePersonViewPresenterController.
 * This class represents a mock of this class mentioned above.
 */
public class InsertDataWindow {

	private String txtName = "txtName";

	private String txtLastName = "txtLastName";

	private String txtWeight = "txtWeight";

	private String txtHeight = "txtHeight";

	private static String BTN_ADD_AND_CONFIRM = "BTN_ADD_AND_CONFIRM";

	private static String BTN_SHOW_TABLE = "BTN_SHOW_TABLE";

	private ObservableList<Person> personDataToRetrieve;

	private TablePersonActionController tpc;

	private Person personToAdd;

	//private PersonOverviewController poc;

	/**
	 * Constructor
	 *
	 * in PageObject corresponds to:
	 * open() > driver.navigate().to(url);
	 *
	 * It instantiates table in background to initialize all data.
	 */
	public InsertDataWindow(){
		tpc = new TablePersonActionController();
		personDataToRetrieve = tpc.getPersonData();
		personToAdd = new Person();
	}

	/**
	 * This method fills out the Textfields
	 * and at the end of the method, it assumes that the user clicks ok.
	 * Then the form is sent, and the person data is added automatically to the Table.
	 * @param person
	 * @return
	 */
	public InsertDataWindow fillInInfo(Person person){
		printText(txtName, person.getFirstName());					//printText("title", post.getTitle());
		printText(txtLastName, person.getLastName());				//printText("text", post.getText());
		printText(txtHeight, String.valueOf(person.getHeight()));
		printText(txtWeight, String.valueOf(person.getWeight()));

		personToAdd = person;

		return this;
	}

	public TableResultsWindow AddPerson(){
		click(BTN_ADD_AND_CONFIRM);

		return new TableResultsWindow(tpc);
	}
									//click("addPostBtn");
	public TableResultsWindow showTableResults(){
		click(BTN_SHOW_TABLE);

		return new TableResultsWindow(personDataToRetrieve);
	}



	//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------


	/**
	 * It gets the element/component of the UI (textfield Name, for example)
	 * to fill in with some text.
	 *
	 * element = "";							//textBox.clear();
     * element = text;							//textBox.sendKeys(text);
	 *
	 * @param element
	 * @param text
	 */
	private void printText(String element, String text){
		element = "";
		element = text;
	}



	/**
	 * It gets the button of the UI to be clicked and do some action.
	 * @param element
	 */
	private void click(String element){

		if(element == BTN_ADD_AND_CONFIRM){
			tpc.addNewPerson(personToAdd);
		}
		else if(element == BTN_SHOW_TABLE){
			personDataToRetrieve = tpc.getPersonData();
		}
	}


	//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------


}
