package result;

import model.Event;
import java.util.ArrayList;

/**
 * Result of MultiEventService
 */
public class MultiEventResult extends ParentResult {
    /**
     * The events found by the service
     */
    private ArrayList<Event> events;

    /**
     * Creates a unsuccessful service result
     * @param success signifies the failure of the service
     * @param message error message of the service
     */
    public MultiEventResult(boolean success, String message) {
        super(success, message);
    }

    /**
     * Creates a successful service result
     * @param success signifies the success of the service
     * @param events the events the service was able to find
     */
    public MultiEventResult(boolean success, ArrayList<Event> events) {
        super(success);
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
