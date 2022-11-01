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
    private ArrayList<Event> data;

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
     * @param data the events the service was able to find
     */
    public MultiEventResult(boolean success, ArrayList<Event> data) {
        super(success);
        this.data = data;
    }

    public ArrayList<Event> getEvents() {
        return data;
    }

    public void setEvents(ArrayList<Event> data) {
        this.data = data;
    }
}
