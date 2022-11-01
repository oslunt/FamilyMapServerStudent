package service;

import dao.AuthtokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Authtoken;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.MultiPersonRequest;
import result.MultiPersonResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class MultiPersonServiceTest {

    private Person randPerson;
    private Person randPerson2;
    private Person randPerson3;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        randPerson = new Person("person123", "username123", "bob", "temp", "M");
        randPerson2 = new Person("person456", "username456", "mary", "temp", "F", "person123","", "");
        randPerson3 = new Person("person789", "username123", "joe", "temp", "M");

        Authtoken randAuthtoken = new Authtoken("Authtoken123", "username123");

        Connection conn = db.getConnection();

        PersonDAO pDao = new PersonDAO(conn);
        pDao.clear();
        pDao.insert(randPerson);
        pDao.insert(randPerson2);
        pDao.insert(randPerson3);

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
    public void personPass() throws DataAccessException {
        MultiPersonService service = new MultiPersonService();
        MultiPersonResult result = service.person(new MultiPersonRequest("Authtoken123"));
        assertEquals(2, result.getPersons().size());
    }

    @Test
    public void personFail() throws DataAccessException {
        MultiPersonService service = new MultiPersonService();
        MultiPersonResult result = service.person(new MultiPersonRequest("Authtoken456"));
        assertFalse(result.isSuccess());
    }
}