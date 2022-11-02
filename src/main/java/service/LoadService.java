package service;

import dao.*;
import model.User;
import request.LoadRequest;
import result.ClearResult;
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
        if(lr.getUsers() == null && lr.getPersons() == null && lr.getEvents() == null) {
            return new LoadResult(false, "Error: nothing to load");
        }
        Database db = new Database();
        Connection conn = null;
        try {
            //I was having some errors with data left over in users when a user passes in null for users
            //To avoid that error I just needed to check that users were null before clearing
            ClearService s = new ClearService();
            ClearResult r;

            if(lr.getUsers() != null) {
                r = s.clear();
            }
            else {
                r = new ClearResult(true, "Success");
            }
            conn = db.openConnection();
            boolean success = true;
            String message = "";
            int usersSize = 0;
            int personsSize = 0;
            int eventsSize = 0;

            if(r.isSuccess()) {

                //I was having the same problems with null as mentioned above so just checking to make sure it was not null
                //Getting the size before to check to make sure everything will be inserted correctly
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
            }
            else {
                return new LoadResult(false, "Error: failed to clear");
            }

        } catch (DataAccessException e) {
            return new LoadResult(false, "DataAcessException Error");
        }
    }
}
