package request;

/**
 * Request object for LoginService
 */
public class LoginRequest {

    /**
     * Username to log in with
     */
    private String username;

    /**
     * Username's password
     */
    private String password;

    /**
     * Creates the login request with username and password
     * @param username username to login for
     * @param password password for the username
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
