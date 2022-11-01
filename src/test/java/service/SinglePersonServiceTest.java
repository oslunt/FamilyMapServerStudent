package service;

import dao.*;
import model.Authtoken;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passoffresult.PersonResult;
import request.SinglePersonRequest;
import result.SinglePersonResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class SinglePersonServiceTest {

    Person randPerson;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        randPerson  = new Person("person123", "username123", "bob", "temp", "M");
        Authtoken randAuthtoken = new Authtoken("Authtoken123", "username123");

        Connection conn = db.getConnection();

        PersonDAO pDao = new PersonDAO(conn);
        pDao.clear();
        pDao.insert(randPerson);

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
        SinglePersonService service = new SinglePersonService();
        SinglePersonResult found = service.person(new SinglePersonRequest("person123", "Authtoken123"));
        assertEquals(randPerson.getPersonID(), found.getPersonID());
        assertEquals(randPerson.getAssociatedUsername(), found.getAssociatedUsername());
        assertEquals(randPerson.getFirstName(), found.getFirstName());
        assertEquals(randPerson.getLastName(), found.getLastName());
        assertEquals(randPerson.getGender(), found.getGender());
    }

    @Test
    public void personFail() throws DataAccessException {
        SinglePersonService service = new SinglePersonService();
        SinglePersonResult found = service.person(new SinglePersonRequest("person456", "Authtoken123"));
        assertFalse(found.isSuccess());
        found = service.person(new SinglePersonRequest("person123", "Authtoken456"));
        assertFalse(found.isSuccess());
    }
}