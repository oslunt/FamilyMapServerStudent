package dao;

import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class PersonDAOTest {
    private Database db;
    private Person randPerson;
    private Person randPerson2;
    private Person randPerson3;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        randPerson = new Person("person123", "username123", "bob", "temp", "M");
        randPerson2 = new Person("person456", "username456", "mary", "temp", "F", "person123","", "");
        randPerson3 = new Person("person789", "username123", "joe", "temp", "M");

        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
        pDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insert(randPerson);
        Person compareTest = pDao.find(randPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(randPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(randPerson);

        assertThrows(DataAccessException.class, () -> pDao.insert(randPerson));
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insert(randPerson2);
        Person compareTest = pDao.find(randPerson2.getPersonID());
        assertNotNull(compareTest);
        assertEquals(randPerson2, compareTest);
    }

    @Test
    public void findFail() throws  DataAccessException {
        assertNull(pDao.find("abc123"));
    }

    @Test
    public void clear() throws DataAccessException {
        pDao.insert(randPerson);
        pDao.insert((randPerson2));
        ArrayList<Person> persons = pDao.returnAll();
        assertEquals(2, persons.size());
        pDao.clear();
        persons = pDao.returnAll();
        assertNull(persons);
    }

    @Test
    public void returnAllPass() throws DataAccessException {
        pDao.insert(randPerson);
        pDao.insert(randPerson2);
        assertEquals(2, pDao.returnAll().size());
    }

    @Test
    public void returnAllFail() throws DataAccessException {
        assertNull(pDao.returnAll());
    }

    @Test
    public void clearUserPass() throws DataAccessException {
        pDao.insert(randPerson);
        pDao.insert(randPerson2);
        pDao.insert(randPerson3);
        pDao.clearUser("username123");
        assertEquals(1, pDao.returnAll().size());
    }

    @Test
    public void clearUserFail() throws DataAccessException {
        pDao.insert(randPerson);
        pDao.insert(randPerson3);
        pDao.clearUser("username456");
        assertEquals(2, pDao.returnAll().size());
    }

    @Test
    public void findFamilyPersonsPass() throws DataAccessException {
        pDao.insert(randPerson);
        pDao.insert(randPerson3);
        pDao.insert(randPerson2);
        assertEquals(2, pDao.findFamilyPersons("username123").size());
    }

    @Test
    public void findFamilyPersonsFail() throws DataAccessException {
        pDao.insert(randPerson2);
        assertNull(pDao.findFamilyPersons("username123"));
    }
}
