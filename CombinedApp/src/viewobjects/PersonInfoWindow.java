package viewobjects;

import org.apache.commons.lang3.math.NumberUtils;

import controller.OverviewActionController;
import javafx.collections.ObservableList;
import model.Person;

public class PersonInfoWindow {

	private String txtName = "txtName";

	private String txtLastName = "txtLastName";

	private String txtWeight = "txtWeight";

	private String txtHeight = "txtHeight";

	private static String BTN_INSERT = "BTN_INSERT";

	private ObservableList<Person> personDataToRetrieve;

	private OverviewActionController overviewController;

	private Person personToAdd;
	
	private TableWindow tw;
	
	private Person personToEditSent;
	
	public PersonInfoWindow(){

		overviewController = new OverviewActionController();		//PageObject corresponds to: driver.navigate().to(url); 
		personDataToRetrieve = overviewController.getPersonData(); 
		personToAdd = new Person();
		
	}

	public PersonInfoWindow(Person personToEdit) {
		personToEditSent = personToEdit;
		
		setPersonInfoWindow(personToEditSent);
	}

	/**
	 * This method fills out the Textfields
	 * and at the end of the method, it assumes that the user clicks ok.
	 * Then the form is sent, and the person data is added automatically to the Table.
	 * @param person
	 * @return
	 */
	public TableWindow fillInInfo(Person person){
		printText(txtName, person.getFirstName());					//printText("title", post.getTitle());
		printText(txtLastName, person.getLastName());				//printText("text", post.getText());
		printText(txtHeight, String.valueOf(person.getHeight()));	
		printText(txtWeight, String.valueOf(person.getWeight()));

		personToAdd = person;

		click(BTN_INSERT);									//click("addPostBtn");

		setPersonInfoWindow(person);	//sets the window;
					
		return new TableWindow(overviewController);
	}
	
	//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------
	
	
		/**
		 * It gets the element/component of the UI (textfield Name, for example) 
		 * to fill in with some text.
		 * @param element
		 * @param text
		 */
		public void printText(String element, String text){
			element = "";							//textBox.clear();
			element = text;							//textBox.sendKeys(text);
		}

		
		
		/**
		 * It gets the button of the UI to be clicked and do some action.
		 * @param element
		 */
		public void click(String element){

			if(element == BTN_INSERT){
				overviewController.addNewPerson(personToAdd);
			}
			
			/*
			else if(element == BTN_SAVE_CHANGES){
				personDataToRetrieve = overviewController.getPersonData();

				System.out.println("| Name | \t | Last Name | \t | Height | \t | Weight | \t | BMI | \t");
				for (Person p : personDataToRetrieve){
					System.out.println("| " + p.getFirstName() + "\t\t" + p.getLastName() + "\t\t"
							+ p.getHeight() + "\t\t" + p.getWeight() + "\t\t" + p.getImcResult() + " |");
				}
				System.out.println("\n\n");			
			}		*/
		}


		//----------------- Methods that simulates the mouse actions or keyboard actions; ---------------------------------
		
		public void setFields(Person personToBeEdited){
			//if(!newPerson.getFirstName().isEmpty())
			printText(txtName, personToBeEdited.getFirstName());					//printText("title", post.getTitle());
			
			printText(txtLastName, personToBeEdited.getLastName());					//printText("text", post.getText());
			
			printText(txtHeight, String.valueOf(personToBeEdited.getHeight()));	
			
			printText(txtWeight, String.valueOf(personToBeEdited.getWeight()));
		}
		
		public void clearFields(){
			//if(!newPerson.getFirstName().isEmpty())
			printText(txtName, "");					//printText("title", post.getTitle());
			
			printText(txtLastName, "");				//printText("text", post.getText());
			
			printText(txtHeight, "");	
			
			printText(txtWeight, "");
		}
		
		public void setPersonInfoWindow(Person person){
			setTxtName(person.getFirstName());
			setTxtLastName(person.getLastName());
			setTxtHeight(String.valueOf(person.getHeight()));
			setTxtWeight(String.valueOf(person.getWeight()));
		}

		
		/*
		 * Here it's returning an oldPerson if any field is not right.
		 */
		public Person getNewPersonToEdit(Person oldPerson){
			Person person;
			if(		NumberUtils.isNumber(getTxtWeight()) && 
					NumberUtils.isNumber(getTxtHeight())){
					
				person = new Person(getTxtName().toString(), getTxtLastName().toString(), Float.parseFloat(getTxtWeight()), Float.parseFloat(getTxtHeight()));
				return person;
				}
			
			else{
				return oldPerson;
			}
		}
		
		/*
		 * Here if any field is not right, it throws an exception
		 */
		public Person createNewPersonToAdd(){
			personToAdd = new Person(getTxtName().toString(), getTxtLastName().toString(), Float.parseFloat(getTxtWeight()), Float.parseFloat(getTxtHeight()));
			//click(BTN_INSERT);
			return personToAdd;
		}

		
		public PersonInfoWindow getPersonInfoWindow(){
			
			System.out.println("Name: " + txtName + "\nLast Name: " + txtLastName + "\nHeight: " + txtHeight + "\nWeight: " + txtWeight);
			
			return new PersonInfoWindow();
		}
		
		public String getTxtName() {
			return txtName;
		}

		public void setTxtName(String txtName) {
			this.txtName = txtName;
		}

		public String getTxtLastName() {
			return txtLastName;
		}

		public void setTxtLastName(String txtLastName) {
			this.txtLastName = txtLastName;
		}

		public String getTxtWeight() {
			return txtWeight;
		}

		public void setTxtWeight(String txtWeight) {
			this.txtWeight = txtWeight;
		}

		public String getTxtHeight() {
			return txtHeight;
		}

		public void setTxtHeight(String txtHeight) {
			this.txtHeight = txtHeight;
		}
	
}
