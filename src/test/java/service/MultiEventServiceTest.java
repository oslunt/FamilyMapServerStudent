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
import request.MultiEventRequest;
import result.MultiEventResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class MultiEventServiceTest {
    private Event randEvent;
    private Event randEvent2;
    private Event randEvent3;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        randEvent = new Event("Biking_123A", "username123", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        randEvent2 = new Event("Biking_123B", "username456", "Gabe123B",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        randEvent3 = new Event("Biking_123C", "username123", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        Authtoken randAuthtoken = new Authtoken("Authtoken123", "username123");

        Connection conn = db.getConnection();

        EventDAO eDao = new EventDAO(conn);
        eDao.clear();
        eDao.insert(randEvent);
        eDao.insert(randEvent2);
        eDao.insert(randEvent3);

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
    public void eventPass() {
        MultiEventService service = new MultiEventService();
        MultiEventResult result = service.event(new MultiEventRequest("Authtoken123"));
        assertEquals(2, result.getEvents().size());
    }

    @Test
    public void eventFail() {
        MultiEventService service = new MultiEventService();
        MultiEventResult result = service.event(new MultiEventRequest("Authtoken456"));
        assertFalse(result.isSuccess());
    }
}