package service;

import dao.AuthtokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.Event;
import request.SingleEventRequest;
import model.Authtoken;
import result.MultiEventResult;
import result.SingleEventResult;

import java.sql.Connection;

/**
 * Returns the single Event object with the specified ID (if the event is associated with the current user).
 * The current user is determined by the provided authtoken
 */
public class SingleEventService {

    /**
     * Returns the single Event object with the specified ID (if the event is associated with the current user).
     * The current user is determined by the provided authtoken
     * @param ser The eventID and authtoken of the current logged-in user
     * @return SingleEventResult that indicates the success/failure of the operation
     */
    public SingleEventResult event(SingleEventRequest ser) {
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();

            AuthtokenDAO aDao = new AuthtokenDAO(conn);
            if(aDao.find(ser.getAuthtoken()) != null) {
                Authtoken a = aDao.find(ser.getAuthtoken());
                EventDAO eDAO = new EventDAO(conn);

                if(eDAO.find(ser.getEventID()) != null  && eDAO.find(ser.getEventID()).getAssociatedUsername().equals(a.getUsername())) {
                    Event e = eDAO.find(ser.getEventID());

                    db.closeConnection(true);
                    return new SingleEventResult(true, e.getAssociatedUsername(), e.getEventID(), e.getPersonID(), e.getLatitude(), e.getLongitude(), e.getCountry(), e.getCity(), e.getEventType(), e.getYear());
                }
                else {
                    db.closeConnection(true);
                    return new SingleEventResult(false, "Error: EventID not found");
                }
            }
            else {
                db.closeConnection(true);
                return new SingleEventResult(false, "Error: Authtoken invalid");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
