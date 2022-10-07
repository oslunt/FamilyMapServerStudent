package model;

import java.util.Objects;

public class Authtoken {
    private String authtoken;
    private String username;

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

    @Override
    public int hashCode() {
        return authtoken.hashCode() + username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Authtoken authtoken1 = (Authtoken) obj;
        return Objects.equals(authtoken, authtoken1.authtoken) && Objects.equals(username, authtoken1.username);
    }

    @Override
    public String toString() {
        return username + ", " + authtoken;
    }
}
