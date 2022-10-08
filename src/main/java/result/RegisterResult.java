package result;

/**
 * Result of RegisterService
 */
public class RegisterResult {
    /**
     * Authtoken generated from register
     */
    private String authtoken;

    /**
     * Logged in username
     */
    private String username;

    /**
     * Associated personId for given username
     */
    private String personID;

    /**
     * Success of the operation
     */
    private boolean success;

    /**
     * Error message if register failed
     */
    private String message;

    /**
     * Unsuccessful register attempt result
     * @param success failure of register attempt
     * @param message reason why register failed
     */
    public RegisterResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Successful register attempt
     * @param success success of register attempt
     * @param authtoken generated authtoken of register
     * @param username username of logged-in user
     * @param personID associated personID for logged-in user
     */
    public RegisterResult(boolean success, String authtoken, String username, String personID) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public String getUsername() {
        return username;
    }

    public String getPersonID() {
        return personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
