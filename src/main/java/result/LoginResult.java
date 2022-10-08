package result;

/**
 * Result of LoginService
 */
public class LoginResult {

    /**
     * Authtoken generated from login
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
     * Error message if login failed
     */
    private String message;

    /**
     * Unsuccessful login attempt result
     * @param success failure of login attempt
     * @param message reason why login failed
     */
    public LoginResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Successful login attempt
     * @param success success of login attempt
     * @param authtoken generated authtoken of login
     * @param username username of logged-in user
     * @param personID associated personID for logged-in user
     */
    public LoginResult(boolean success, String authtoken, String username, String personID) {
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
