package service;

import request.SingleEventRequest;
import model.Authtoken;
import result.SingleEventResult;

public class SingleEventService {

    /**
     * Returns the single Event object with the specified ID (if the event is associated with the current user).
     * The current user is determined by the provided authtoken
     * @param ser The eventID and authtoken of the current logged-in user
     * @return SingleEventResult that indicates the success/failure of the operation
     */
    public SingleEventResult event(SingleEventRequest ser) {
        return null;
    }
}
