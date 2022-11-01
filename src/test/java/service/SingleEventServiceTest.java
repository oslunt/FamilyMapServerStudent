package service;

import dao.AuthtokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.Authtoken;
import model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.SingleEventRequest;
import result.SingleEventResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class SingleEventServiceTest {
    private Event randEvent;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        randEvent = new Event("Biking_123A", "username123", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        Authtoken randAuthtoken = new Authtoken("Authtoken123", "username123");

        Connection conn = db.getConnection();

        EventDAO eDao = new EventDAO(conn);
        eDao.clear();
        eDao.insert(randEvent);

        AuthtokenDAO aDao = new AuthtokenDAO(conn);
        aDao.clear();
        aDao.insert(randAuthtoken);

        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() {
        ClearService s = new ClearService();
        try {
            s.clear();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void EventPass() throws DataAccessException {
        SingleEventService service = new SingleEventService();
        SingleEventResult found = service.event(new SingleEventRequest("Biking_123A", "Authtoken123"));
        assertEquals(randEvent.getEventID(), found.getEventID());
        assertEquals(randEvent.getAssociatedUsername(), found.getAssociatedUsername());
        assertEquals(randEvent.getPersonID(), found.getPersonID());
        assertEquals(randEvent.getLatitude(), found.getLatitude());
        assertEquals(randEvent.getLongitude(), found.getLongitude());
        assertEquals(randEvent.getCountry(), found.getCountry());
        assertEquals(randEvent.getCity(), found.getCity());
        assertEquals(randEvent.getEventType(), found.getEventType());
        assertEquals(randEvent.getYear(), found.getYear());
    }

    @Test
    public void EventFail() throws DataAccessException {
        SingleEventService service = new SingleEventService();
        SingleEventResult found = service.event(new SingleEventRequest("Event456", "Authtoken123"));
        assertFalse(found.isSuccess());
        found = service.event(new SingleEventRequest("Event123", "Authtoken456"));
        assertFalse(found.isSuccess());
    }
}