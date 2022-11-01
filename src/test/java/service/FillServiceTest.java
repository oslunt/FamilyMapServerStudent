package service;

import dao.*;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.FillRequest;
import result.FillResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class FillServiceTest {
    User randUser;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();

        randUser = new User("user123", "password123", "bobtemp@email.com", "bob", "temp", "M", "bob_temp");

        UserDAO uDao = new UserDAO(conn);
        uDao.clear();
        uDao.insert(randUser);
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
    public void fillPass() throws DataAccessException {
        FillService service = new FillService();
        FillResult result = service.fill(new FillRequest("user123"));
        Database db = new Database();
        Connection conn = db.getConnection();

        PersonDAO pDao = new PersonDAO(conn);
        assertEquals(31, pDao.findFamilyPersons("user123").size());

        EventDAO eDao = new EventDAO(conn);
        assertEquals(91, eDao.findFamilyEvents("user123").size());
        db.closeConnection(false);
    }

    @Test
    public void fillFail() throws DataAccessException {
        FillService service = new FillService();
        FillResult result = service.fill(new FillRequest("user456"));
        assertFalse(result.isSuccess());
    }
}