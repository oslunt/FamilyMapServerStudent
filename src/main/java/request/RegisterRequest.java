package request;

public class RegisterRequest {

    /**
     * New username to register
     */
    private String username;

    /**
     * New password to register
     */
    private String password;

    /**
     * New email to register
     */
    private String email;

    /**
     * New firstName to register
     */
    private String firstName;

    /**
     * New lastName to register
     */
    private String lastName;

    /**
     * New gender to register
     */
    private String gender;

    /**
     * The parameters needed to then perform a register request
     * @param username username is new and unique
     * @param password password to register
     * @param email email to register
     * @param firstName firstName to register
     * @param lastName lastName to register
     * @param gender gender to register
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
