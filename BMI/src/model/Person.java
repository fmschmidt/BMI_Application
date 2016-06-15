package model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import application.LocalDateAdapter;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Felipe Schmidt
 */
public class Person {

    private String firstName;
    private String lastName;
    private Float weight;
    private Float height;
    private Float imcResult;
    private LocalDate date;


    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Some initial dummy data, just for convenient testing.
        this.weight = 70.0f;
        this.height = 1.70f;
        this.imcResult = 70.0f/(1.70f*1.70f);
        this.date = (LocalDate.of(2015, 11, 27));
    }

    /**
     * Constructor to add new people.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName, float weight, float height) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Some initial dummy data, just for convenient testing.
        this.weight = weight;
        this.height = height;
        this.imcResult = (weight)/(height*height);
        this.date = (LocalDate.now());
    }

    /**
     * Constructor WITH IMC RESULT AND DATE.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName, float weight, float height, float imcResult, LocalDate date) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Some initial dummy data, just for convenient testing.
        this.weight = weight;
        this.height = height;
        this.imcResult = imcResult;
        this.date = date;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getImcResult() {
		return imcResult;
	}

	public void setImcResult(Float imcResult) {
		this.imcResult = imcResult;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


}