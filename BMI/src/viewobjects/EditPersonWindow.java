package viewobjects;

import javafx.collections.ObservableList;
import model.Person;
import controller.EditPersonViewActionController;
import controller.TablePersonActionController;

public class EditPersonWindow {
	private String OK = "OK";
	
	private String CANCEL = "CANCEL";
	
	private String txtName = "txtName";

	private String txtLastName = "txtLastName";

	private String txtWeight = "txtWeight";

	private String txtHeight = "txtHeight";
	
	private ObservableList<Person> personDataToRetrieve;
	
	private EditPersonViewActionController peac;
	
	private TablePersonActionController tpac;

	private Person oldPerson;
	
	public Person personToBeEdited;
	
	/**
	 * Constructor
	 */
	public EditPersonWindow() {
		personToBeEdited = new Person();
		oldPerson = new Person();
		peac = new EditPersonViewActionController();
		
		tpac = new TablePersonActionController();				//instantiates table in background to initialize all data
		personDataToRetrieve = tpac.getPersonData(); 
	}
	/**
	 * Constructor
	 * The only way of opening this dialog window is when we have a person to be edited.
	 * Otherwise it's never instantiated.
	 * @param personToEdit
	 */
	public EditPersonWindow(Person personToEdit) {
		peac = new EditPersonViewActionController();
		personToBeEdited = new Person();
		personToBeEdited = personToEdit;
	}

	
	public TableResultsWindow editPersonCancel(Person person){
		click(CANCEL);
		
		return new TableResultsWindow();
	}
	
	public TableResultsWindow editPersonDetails(Person oldPersonSelected, Person newPerson){
		oldPerson = oldPersonSelected;
		personToBeEdited = newPerson;
		//if(!newPerson.getFirstName().isEmpty())
		printText(txtName, newPerson.getFirstName());					//printText("title", post.getTitle());
		
		printText(txtLastName, newPerson.getLastName());				//printText("text", post.getText());
		
		printText(txtHeight, String.valueOf(newPerson.getHeight()));	
		
		printText(txtWeight, String.valueOf(newPerson.getWeight()));

		
		
		click(OK);
		
		return new TableResultsWindow(tpac);
	}
	
	
	
	//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------
	

	/**
	 * It gets the element/component of the UI (textfield Name, for example) 
	 * to fill in with some text.
	 * @param element
	 * @param text
	 */
	private void printText(String element, String text){
		element = "";							//textBox.clear();
		element = text;							//textBox.sendKeys(text);
	}

	
	/**
	 * It gets the button of the UI to be clicked and do some action.
	 * @param element
	 */
	private void click(String element){

		if(element == OK){
			//pec.editPerson(personToBeEdited);
			peac.saveEditedPerson(oldPerson, personToBeEdited, personDataToRetrieve);
		}
		if(element == CANCEL){
			
		}
			
	}
	
	
	//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------
	
}
