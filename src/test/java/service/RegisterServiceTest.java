package service;

import dao.AuthtokenDAO;
import dao.UserDAO;
import dao.DataAccessException;
import dao.Database;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.RegisterResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {

    User randUser;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();

        randUser = new User("user123", "password123", "bobtemp@email.com", "bob", "temp", "M", "bob_temp");

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
    public void registerPass() throws DataAccessException {
        RegisterService r = new RegisterService();
        RegisterResult result = r.register(new RegisterRequest(randUser.getUsername(), randUser.getPassword(), randUser.getEmail(), randUser.getFirstName(), randUser.getLastName(), randUser.getGender()));
        assertTrue(result.isSuccess());
        assertEquals(randUser.getUsername(), result.getUsername());
        assertNotNull(result.getAuthtoken());
        assertEquals(randUser.getPersonID(), result.getPersonID());
    }

    @Test
    public void registerFail() throws DataAccessException {
        RegisterService service = new RegisterService();
        RegisterResult result = service.register(new RegisterRequest(randUser.getUsername(), randUser.getPassword(), randUser.getEmail(), randUser.getFirstName(), randUser.getLastName(), randUser.getGender()));
        assertTrue(result.isSuccess());
        result = service.register(new RegisterRequest(randUser.getUsername(), randUser.getPassword(), randUser.getEmail(), randUser.getFirstName(), randUser.getLastName(), randUser.getGender()));
        assertFalse(result.isSuccess());
    }
}