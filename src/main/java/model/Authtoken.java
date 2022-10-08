package model;

import java.util.Objects;

public class Authtoken {

    /**
     * Unique authtoken
     */
    private String authtoken;

    /**
     * Username that is associated with the authtoken
     */
    private String username;

    /**
     * Creates the Authtoken object
     * @param authtoken unique authtoken
     * @param username username associated with the authtoken
     */
    public Authtoken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Overridden hashcode method to incorporate all parts of object
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return authtoken.hashCode() + username.hashCode();
    }

    /**
     * Overridden equals to method to check equality of all parts of the object
     * @param obj object to compare to
     * @return boolean of true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Authtoken authtoken1 = (Authtoken) obj;
        return Objects.equals(authtoken, authtoken1.authtoken) && Objects.equals(username, authtoken1.username);
    }

    /**
     * Overriden toString method to display data
     * @return object to string
     */
    @Override
    public String toString() {
        return username + ", " + authtoken;
    }
}
