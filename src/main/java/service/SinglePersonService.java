package service;

import request.SinglePersonRequest;
import result.SinglePersonResult;
import model.Authtoken;

/**
 * Returns the single Person object with the specified ID (if the person is associated with the current user).
 * The current user is determined by the provided authtoken.
 */
public class SinglePersonService {

    /**
     * Returns the single Person object with the specified ID (if the person is associated with the current user).
     * The current user is determined by the provided authtoken.
     * @param spr Contains the personId and authtoken of current user
     * @return SinglePersonResult that indicates the success/failure of the operation
     */
    public SinglePersonResult person(SinglePersonRequest spr) {
        return null;
    }
}
