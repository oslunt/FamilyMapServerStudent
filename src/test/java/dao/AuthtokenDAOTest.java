package dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import model.Authtoken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;


class AuthtokenDAOTest {

    private Database db;
    private Authtoken authtoken;
    private AuthtokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        authtoken = new Authtoken("123abc", "tempUsr");
        Connection conn = db.getConnection();
        aDao = new AuthtokenDAO(conn);
        aDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    void insert() {
    }

    @Test
    void find() {
    }

    @Test
    void clear() {

    }
}