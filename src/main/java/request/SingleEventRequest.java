package request;

/**
 * Request object for SingleEventService
 */
public class SingleEventRequest {

    /**
     * The eventID to search for
     */
    private String eventID;

    /**
     * The authtoken of the logged-in user
     */
    private String authtoken;

    /**
     * Creates object to run the service
     * @param eventID eventID to search for
     * @param authtoken authtoken of the logged-in user
     */
    public SingleEventRequest(String eventID, String authtoken) {
        this.eventID = eventID;
        this.authtoken = authtoken;
    }

    public String getEventID() {
        return eventID;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
