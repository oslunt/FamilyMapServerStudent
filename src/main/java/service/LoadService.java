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
            boolean success = true;
            String message = "";
            int usersSize = 0;
            int personsSize = 0;
            int eventsSize = 0;

            if(lr.getUsers() != null) {
                UserDAO uDao = new UserDAO(conn);
                int startingU = 0;
                if(uDao.returnAll() != null) {
                    startingU = uDao.returnAll().size();
                }
                for(int i = 0; i < lr.getUsers().size(); i++) {
                    uDao.insert(lr.getUsers().get(i));
                }
                usersSize = lr.getUsers().size();
                if(usersSize != uDao.returnAll().size() - startingU) {
                    success = false;
                    message += "\nError adding users";
                }
            }

            if(lr.getPersons() != null) {
                PersonDAO pDao = new PersonDAO(conn);
                int startingP = 0;
                if(pDao.returnAll() != null) {
                    startingP = pDao.returnAll().size();
                }
                for(int i = 0; i < lr.getPersons().size(); i++) {
                    pDao.insert(lr.getPersons().get(i));
                }
                personsSize = lr.getPersons().size();
                if(personsSize != pDao.returnAll().size() - startingP) {
                    success = false;
                    message += "\nError adding persons";
                }
            }

            if(lr.getEvents() != null) {
                EventDAO eDao = new EventDAO(conn);
                int startingE = 0;
                if(eDao.returnAll() != null) {
                    startingE = eDao.returnAll().size();
                }
                for(int i = 0; i < lr.getEvents().size(); i++) {
                    eDao.insert(lr.getEvents().get(i));
                }
                eventsSize = lr.getEvents().size();
                if(eventsSize != eDao.returnAll().size() - startingE) {
                    success = false;
                    message += "\nError adding events";
                }
            }


            if(message.length() == 0) {
                message = "Successfully added " + usersSize + " users, " + personsSize + " persons, and " + eventsSize + " events to the database.";
            }

            db.closeConnection(true);
            return new LoadResult(success, message);
        } catch (DataAccessException e) {
            return new LoadResult(false, "DataAcessException Error");
        }
    }
}
