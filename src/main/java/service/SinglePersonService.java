package service;

import dao.AuthtokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Person;
import request.SinglePersonRequest;
import result.SinglePersonResult;
import result.SinglePersonResult;
import model.Authtoken;

import java.sql.Connection;

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
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();

            AuthtokenDAO aDao = new AuthtokenDAO(conn);
            if(aDao.find(spr.getAuthtoken()) != null) {
                PersonDAO pDAO = new PersonDAO(conn);

                if(pDAO.find(spr.getPersonID()) != null) {
                    Person p = pDAO.find(spr.getPersonID());

                    db.closeConnection(true);
                    return new SinglePersonResult(true, p.getAssociatedUsername(), p.getPersonID(), p.getFirstName(), p.getLastName(), p.getGender(), p.getFatherID(), p.getMotherID(), p.getSpouseID());
                }
                else {
                    db.closeConnection(true);
                    return new SinglePersonResult(false, "PersonID not found");
                }
            }
            else {
                db.closeConnection(true);
                return new SinglePersonResult(false, "Authtoken invalid");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
