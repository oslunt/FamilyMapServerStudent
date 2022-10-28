package service;

import dao.*;
import model.User;
import request.LoadRequest;
import result.LoadResult;

import java.sql.Connection;

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
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();
            UserDAO uDao = new UserDAO(conn);
            PersonDAO pDao = new PersonDAO(conn);
            EventDAO eDao = new EventDAO(conn);

            int startingU = uDao.returnAll().size();
            int startingP = pDao.returnAll().size();
            int startingE = eDao.returnAll().size();

            for(int i = 0; i < lr.getUsers().size(); i++) {
                uDao.insert(lr.getUsers().get(i));
            }
            for(int i = 0; i < lr.getPersons().size(); i++) {
                pDao.insert(lr.getPersons().get(i));
            }
            for(int i = 0; i < lr.getEvents().size(); i++) {
                eDao.insert(lr.getEvents().get(i));
            }

            boolean success = true;
            String message = "";
            if(lr.getUsers().size() != uDao.returnAll().size() - startingU) {
                success = false;
                message += "\nError adding users";
            }
            if(lr.getPersons().size() != pDao.returnAll().size() - startingP) {
                success = false;
                message += "\nError adding persons";
            }
            if(lr.getEvents().size() != eDao.returnAll().size() - startingE) {
                success = false;
                message += "\nError adding events";
            }
            if(message.length() == 0) {
                message = "Successfully added " + lr.getUsers().size() + " users, " + lr.getPersons().size() + " persons, and " + lr.getEvents().size() + "events to the database.";
            }

            db.closeConnection(true);
            return new LoadResult(success, message);
        } catch (DataAccessException e) {
            return new LoadResult(false, "DataAcessException Error");
        }
    }
}
