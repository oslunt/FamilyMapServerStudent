package dao;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class UserDAOTest {
    private Database db;
    private User randUser;
    private User randUser2;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        randUser = new User("user123", "password123", "bobtemp@email.com", "bob", "temp", "M", "person123");
        randUser2 = new User("user456", "password456", "mary@email.com", "mary", "temp", "F","person456");

        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(randUser);
        User compareTest = uDao.find(randUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(randUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(randUser);

        assertThrows(DataAccessException.class, () -> uDao.insert(randUser));
    }

    @Test
    public void findPass() throws DataAccessException {
        uDao.insert(randUser2);
        User compareTest = uDao.find(randUser2.getUsername());
        assertNotNull(compareTest);
        assertEquals(randUser2, compareTest);
    }

    @Test
    public void findFail() throws  DataAccessException {
        assertNull(uDao.find("abc123"));
    }

    @Test
    public void clear() throws DataAccessException {
        uDao.insert(randUser);
        uDao.insert((randUser2));
        ArrayList<User> users = uDao.returnAll();
        assertEquals(2, users.size());
        uDao.clear();
        users = uDao.returnAll();
        assertNull(users);
    }
}
