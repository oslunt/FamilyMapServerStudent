package request;

/**
 * Request object for MultiEventRequest
 */
public class MultiEventRequest {

    /**
     * The authtoken of logged-in user
     */
    private String authtoken;

    /**
     * Creates the object to get events
     * @param authtoken Authtoken of current logged-in user
     */
    public MultiEventRequest(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
