package model;

import java.util.Objects;

public class User {

    /**
     * Unique username for user
     */
    private String username;

    /**
     * User’s password
     */
    private String password;

    /**
     * User’s email address
     */
    private String email;

    /**
     * User’s first name
     */
    private String firstName;

    /**
     * User’s last name
     */
    private String lastName;

    /**
     * User’s gender
     */
    private String gender;

    /**
     * Unique Person ID assigned to this user’s generated Person
     */
    private String personID;

    /**
     * Constructor to create a user object
     * @param username Unique username for user
     * @param password User’s password
     * @param email User’s email address
     * @param firstName User’s first name
     * @param lastName User’s last name
     * @param gender User’s gender
     * @param personID Unique Person ID assigned to this user’s generated Person
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
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

    public String getPersonID() {
        return personID;
    }

    /**
     * Overridden hashcode method to incorporate all parts of object
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return username.hashCode() + password.hashCode() + email.hashCode() + firstName.hashCode() + lastName.hashCode() + gender.hashCode() + personID.hashCode();
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
        User user = (User) obj;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(personID, user.personID);
    }

    /**
     * Overriden toString method to display data
     * @return object to string
     */
    @Override
    public String toString() {
        return username + ", " + password + ", " + email + ", " + firstName + ", " + lastName + ", " +  gender + ", " +  personID;
    }
}
