package request;

/**
 * Request object for SinglePersonService
 */
public class SinglePersonRequest {

    /**
     * personId to search for
     */
    private String personID;

    /**
     * Authtoken of logged-in user
     */
    private String authtoken;

    /**
     * Creates the request parameters needed to run the service
     * @param personID personId to search for
     * @param authtoken Logged-in user's authtoken
     */
    public SinglePersonRequest(String personID, String authtoken) {
        this.personID = personID;
        this.authtoken = authtoken;
    }

    public String getPersonID() {
        return personID;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
