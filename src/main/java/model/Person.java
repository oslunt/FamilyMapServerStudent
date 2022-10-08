package model;

import java.util.Objects;

/**
 * A Person
 */
public class Person {

    /**
     * Unique identifier for this person
     */
    private String personID;

    /**
     * Username of user to which this person belongs
     */
    private String associatedUsername;

    /**
     * Person’s first name
     */
    private String firstName;

    /**
     * Person’s last name
     */
    private String lastName;

    /**
     * Person’s gender
     */
    private String gender;

    /**
     * Person ID of person’s father
     */
    private String fatherID;

    /**
     * Person ID of person’s mother
     */
    private String motherID;

    /**
     * Person ID of person’s spouse
     */
    private String spouseID;

    /**
     * Default constructor without Father, mother, nor spouse ids
     * @param personID Unique identifier for this person
     * @param associatedUsername Username of user to which this person belongs
     * @param firstName Person’s first name
     * @param lastName Person’s last name
     * @param gender Person’s gender
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this(personID, associatedUsername, firstName, lastName, gender, "", "", "");
    }

    /**
     * Constructor with father, mother, and spouse ids
     * @param personID Unique identifier for this person
     * @param associatedUsername Username of user to which this person belongs
     * @param firstName Person’s first name
     * @param lastName Person’s last name
     * @param gender Person’s gender
     * @param fatherID Person ID of person’s father
     * @param motherID Person ID of person’s mother
     * @param spouseID Person ID of person’s spouse
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public String getPersonID() {
        return personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    /**
     * Overridden hashcode method to incorporate all parts of object
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return personID.hashCode() + associatedUsername.hashCode() + firstName.hashCode() + lastName.hashCode() + gender.hashCode();
    }

    /**
     * Overridden equals to method to check equality of all parts of the object
     * @param obj object to compare to
     * @return boolean of true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
    }

    /**
     * Overriden toString method to display data
     * @return object to string
     */
    @Override
    public String toString() {
        return personID + ", " + associatedUsername + ", " + firstName + ", " + lastName + ", " + gender + ", " + fatherID + ", " + motherID + ", " + spouseID;
    }
}
