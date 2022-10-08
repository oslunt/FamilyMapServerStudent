package request;

/**
 * Request object for MultiPersonService
 */
public class MultiPersonRequest {

    /**
     * The authtoken of logged-in user
     */
    private String authtoken;

    /**
     * Creates the object to get persons
     * @param authtoken Authtoken of current logged-in user
     */
    public MultiPersonRequest(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
