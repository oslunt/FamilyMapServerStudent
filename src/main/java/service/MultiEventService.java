package service;

import dao.AuthtokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.Event;
import request.MultiEventRequest;
import model.Authtoken;
import result.MultiEventResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Returns ALL events for ALL family members of the current user.
 * The current user is determined from the provided auth token.
 */
public class MultiEventService {

    /**
     * Returns ALL events for ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param mer Authtoken of current logged-in user
     * @return MultiEventResult that indicates the success/failure of the operation
     */
    public MultiEventResult event(MultiEventRequest mer) {
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();

            AuthtokenDAO aDao = new AuthtokenDAO(conn);
            if(aDao.find(mer.getAuthtoken()) != null) {
                String username = aDao.find(mer.getAuthtoken()).getUsername();
                EventDAO eDAO = new EventDAO(conn);

                ArrayList<Event> events = eDAO.findFamilyEvents(username);
                db.closeConnection(true);
                return new MultiEventResult(true, events);
            }
            else {
                db.closeConnection(true);
                return new MultiEventResult(false, "Error: Authtoken invalid");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
