package dao;

import model.Authtoken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import model.Authtoken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;


class AuthtokenDAOTest {

    private Database db;
    private Authtoken randAuthtoken;
    private Authtoken randAuthtoken2;
    private Authtoken randAuthtoken3;
    private AuthtokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        randAuthtoken = new Authtoken("Authtoken123", "username123");
        randAuthtoken2 = new Authtoken("Authtoken456", "username456");
        randAuthtoken3 = new Authtoken("Authtoken789", "username123");

        Connection conn = db.getConnection();
        aDao = new AuthtokenDAO(conn);
        aDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        aDao.insert(randAuthtoken);
        Authtoken compareTest = aDao.find(randAuthtoken.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(randAuthtoken, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        aDao.insert(randAuthtoken);

        assertThrows(DataAccessException.class, () -> aDao.insert(randAuthtoken));
    }

    @Test
    public void findPass() throws DataAccessException {
        aDao.insert(randAuthtoken2);
        Authtoken compareTest = aDao.find(randAuthtoken2.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(randAuthtoken2, compareTest);
    }

    @Test
    public void findFail() throws  DataAccessException {
        assertNull(aDao.find("abc123"));
    }

    @Test
    public void clear() throws DataAccessException {
        aDao.insert(randAuthtoken);
        aDao.insert((randAuthtoken2));
        ArrayList<Authtoken> Authtokens = aDao.returnAll();
        assertEquals(2, Authtokens.size());
        aDao.clear();
        Authtokens = aDao.returnAll();
        assertNull(Authtokens);
    }

    @Test
    public void returnAllPass() throws DataAccessException {
        aDao.insert(randAuthtoken);
        aDao.insert(randAuthtoken2);
        assertEquals(2, aDao.returnAll().size());
    }

    @Test
    public void returnAllFail() throws DataAccessException {
        assertNull(aDao.returnAll());
    }
}