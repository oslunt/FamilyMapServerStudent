package service;

import request.RegisterRequest;
import result.RegisterResult;

public class RegisterService {

    /**
     * Creates a new user account (user row in the database)
     * Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
     * Logs the user in
     * @param r the parameters needed for request
     * @return Returns an authtoken
     */
    public RegisterResult register(RegisterRequest r) {
        return null;
    }
}
