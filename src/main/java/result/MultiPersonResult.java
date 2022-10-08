package result;

import model.Person;

import java.util.ArrayList;

/**
 * Result of MultiPersonService
 */
public class MultiPersonResult {
    /**
     * The persons found by the service
     */
    private ArrayList<Person> persons;

    /**
     * The success of the service
     */
    private boolean success;

    /**
     * The error string of the service
     */
    private String message;

    /**
     * Creates a unsuccessful service result
     * @param success signifies the failure of the service
     * @param message error message of the service
     */
    public MultiPersonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Creates a successful service result
     * @param success signifies the success of the service
     * @param persons the persons the service was able to find
     */
    public MultiPersonResult(boolean success, ArrayList<Person> persons) {
        this.persons = persons;
        this.success = success;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
