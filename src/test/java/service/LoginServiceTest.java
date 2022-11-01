package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import result.LoginResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
    User randUser;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();

        randUser = new User("user123", "password123", "bobtemp@email.com", "bob", "temp", "M", "bob_temp");

        UserDAO uDao = new UserDAO(conn);
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
    public void loginPass() throws DataAccessException {
        LoginService service = new LoginService();
        LoginResult result = service.login(new LoginRequest("user123", "password123"));

        assertTrue(result.isSuccess());
        assertNotNull(result.getAuthtoken());
    }

    @Test
    public void loginFail() throws DataAccessException {
        LoginService service = new LoginService();
        LoginResult result = service.login(new LoginRequest("user123", "password456"));

        assertFalse(result.isSuccess());
    }
}