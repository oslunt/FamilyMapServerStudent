package service;

import dao.*;
import result.ClearResult;

import java.sql.Connection;

/**
 * Deletes ALL data from the database, including user, authtoken, person, and event data
 */
public class ClearService {

    /**
     * Deletes ALL data from the database, including user, authtoken, person, and event data
     * @return the success of the clear operation
     */
    public ClearResult clear() throws DataAccessException {
        Database db = new Database();
        Connection conn = db.openConnection();

        UserDAO uDao = new UserDAO(conn);
        AuthtokenDAO aDao = new AuthtokenDAO(conn);
        PersonDAO pDao = new PersonDAO(conn);
        EventDAO eDao = new EventDAO(conn);

        uDao.clear();
        aDao.clear();
        pDao.clear();
        eDao.clear();

        boolean success = true;
        String message = "";
        if(uDao.returnAll().size() > 0){
            success = false;
            message += "\nUser datatable failed to clear";
        }
        if(aDao.returnAll().size() > 0){
            success = false;
            message += "\nAuthtoken datatable failed to clear";
        }
        if(pDao.returnAll().size() > 0){
            success = false;
            message += "\nPerson datatable failed to clear";
        }
        if(eDao.returnAll().size() > 0){
            success = false;
            message += "\nEvent datatable failed to clear";
        }

        ClearResult result = new ClearResult(success, message);

        db.closeConnection(true);
        return result;
    }
}
