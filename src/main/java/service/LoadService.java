package service;

import request.LoadRequest;
import result.LoadResult;

/**
 * Clears all data from the database (just like the /clear API)
 * Loads the user, person, and event data from the request body into the database.
 */
public class LoadService {

    /**
     * Clears all data from the database (just like the /clear API)
     * Loads the user, person, and event data from the request body into the database.
     * @param lr The loadRequest to be added to the database
     * @return Returns the success or failure of the operation
     */
    public LoadResult load(LoadRequest lr) {
        return null;
    }
}
