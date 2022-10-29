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

        boolean success = true;
        String message = "";

        UserDAO uDao = new UserDAO(conn);
        uDao.clear();
        if(uDao.returnAll() != null){
            success = false;
            message += "\nUser datatable failed to clear";
        }

        AuthtokenDAO aDao = new AuthtokenDAO(conn);
        aDao.clear();
        if(aDao.returnAll() != null){
            success = false;
            message += "\nAuthtoken datatable failed to clear";
        }

        PersonDAO pDao = new PersonDAO(conn);
        pDao.clear();
        if(pDao.returnAll() != null){
            success = false;
            message += "\nPerson datatable failed to clear";
        }

        EventDAO eDao = new EventDAO(conn);
        eDao.clear();
        if(eDao.returnAll() != null){
            success = false;
            message += "\nEvent datatable failed to clear";
        }

        if(message.length() == 0){
            message = "Datatables cleared successfully";
        }

        ClearResult result = new ClearResult(success, message);

        db.closeConnection(true);
        return result;
    }
}
