package result;

/**
 * Result of LoginService
 */
public class LoginResult extends ParentResult {

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
     * Unsuccessful login attempt result
     * @param success failure of login attempt
     * @param message reason why login failed
     */
    public LoginResult(boolean success, String message) {
        super(success, message);
    }

    /**
     * Successful login attempt
     * @param success success of login attempt
     * @param authtoken generated authtoken of login
     * @param username username of logged-in user
     * @param personID associated personID for logged-in user
     */
    public LoginResult(boolean success, String authtoken, String username, String personID) {
        super(success);
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
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

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
