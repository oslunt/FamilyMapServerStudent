package service;

import request.MultiEventRequest;
import model.Authtoken;
import result.MultiEventResult;

public class MultiEventService {

    /**
     * Returns ALL events for ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param mer Authtoken of current logged-in user
     * @return MultiEventResult that indicates the success/failure of the operation
     */
    public MultiEventResult event(MultiEventRequest mer) {
        return null;
    }
}
