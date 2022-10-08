package result;

import model.Event;
import java.util.ArrayList;

public class MultiEventResult {
    /**
     * The events found by the service
     */
    private ArrayList<Event> events;

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
    public MultiEventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Creates a successful service result
     * @param success signifies the success of the service
     * @param events the events the service was able to find
     */
    public MultiEventResult(boolean success, ArrayList<Event> events) {
        this.events = events;
        this.success = success;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
