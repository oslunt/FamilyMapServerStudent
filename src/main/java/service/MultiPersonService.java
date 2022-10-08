package service;

import request.MultiPersonRequest;
import model.Authtoken;
import result.MultiPersonResult;

/**
 * Returns ALL family members of the current user.
 * The current user is determined from the provided auth token.
 */
public class MultiPersonService {
    /**
     * Returns ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param mpr Authtoken of current user
     * @return MultiPersonResult that indicates the success/failure of the operation
     */
    public MultiPersonResult person(MultiPersonRequest mpr) {
        return null;
    }
}
