package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Helper class to wrap a list of persons.
 * Saves the list in XML.
 * 
 * @author Felipe Schmidt
 */
@XmlRootElement(name = "persons")	//defines the name of the root element.
public class PersonListWrapper {

	
	private List<Person> persons;

    @XmlElement(name = "person")	//is an optional name we can specify for the element.
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
