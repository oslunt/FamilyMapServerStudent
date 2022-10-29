package service;

import dao.AuthtokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Event;
import model.Person;
import request.MultiPersonRequest;
import model.Authtoken;
import result.MultiPersonResult;
import result.MultiPersonResult;

import java.sql.Connection;
import java.util.ArrayList;

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
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();

            AuthtokenDAO aDao = new AuthtokenDAO(conn);
            if(aDao.find(mpr.getAuthtoken()) != null) {
                String username = aDao.find(mpr.getAuthtoken()).getUsername();
                PersonDAO pDAO = new PersonDAO(conn);

                ArrayList<Person> persons = pDAO.findFamilyPersons(username);
                db.closeConnection(true);
                return new MultiPersonResult(true, persons);
            }
            else {
                db.closeConnection(true);
                return new MultiPersonResult(false, "Authtoken invalid");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
