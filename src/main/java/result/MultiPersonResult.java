package result;

import model.Person;

import java.util.ArrayList;

/**
 * Result of MultiPersonService
 */
public class MultiPersonResult extends ParentResult {
    /**
     * The persons found by the service
     */
    private ArrayList<Person> data;

    /**
     * Creates a unsuccessful service result
     * @param success signifies the failure of the service
     * @param message error message of the service
     */
    public MultiPersonResult(boolean success, String message) {
        super(success, message);
    }

    /**
     * Creates a successful service result
     * @param success signifies the success of the service
     * @param data the persons the service was able to find
     */
    public MultiPersonResult(boolean success, ArrayList<Person> data) {
        super(success);
        this.data = data;
    }

    public ArrayList<Person> getPersons() {
        return data;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.data = persons;
    }
}
