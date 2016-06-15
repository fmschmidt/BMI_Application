package viewobjects;

import controller.OverviewActionController;
import javafx.collections.ObservableList;
import model.Person;

public class OverviewApplicationWindow {

	private OverviewActionController overviewController;

	private TableWindow tableWindow;

	private PersonInfoWindow personInfoWindow;

	private ObservableList<Person> personDataToRetrieve;


	public OverviewApplicationWindow(){

		personInfoWindow = new PersonInfoWindow();
		tableWindow = new TableWindow();
		overviewController = new OverviewActionController();

		personDataToRetrieve = overviewController.getPersonData();

	}

	public OverviewApplicationWindow(PersonInfoWindow piw, TableWindow tw) {
		//just used to print the left-side on the console
		piw.getPersonInfoWindow();

		//just used to print the right-side on the console
		tw.getTableWindow();
	}


	public OverviewApplicationWindow getCombinedApp(PersonInfoWindow piw, TableWindow tw){
		tableWindow = tw;
		personInfoWindow = piw;

		return new OverviewApplicationWindow(personInfoWindow, tableWindow);
	}

	public OverviewApplicationWindow fillInInfoAndAdd (Person person){
		tableWindow = personInfoWindow.fillInInfo(person);

		tableWindow = tableWindow.assertTableCount(5);

		return new OverviewApplicationWindow(personInfoWindow, tableWindow);

	}

	public OverviewApplicationWindow deletePerson(int index){
		tableWindow = tableWindow.removePerson(index);
		tableWindow = tableWindow.assertTableCount(3);

		return new OverviewApplicationWindow(personInfoWindow, tableWindow);
	}

	public OverviewApplicationWindow editPerson(int index){
		personInfoWindow = tableWindow.editPerson(index);



		return new OverviewApplicationWindow(personInfoWindow, tableWindow);
	}

	public OverviewApplicationWindow editPersonDetails(Person oldPersonSelected, Person newPerson){
		tableWindow = tableWindow.editPersonDetails(oldPersonSelected, newPerson);

		tableWindow = tableWindow.assertTableCount(4);

		return new OverviewApplicationWindow(personInfoWindow, tableWindow);
	}

}
