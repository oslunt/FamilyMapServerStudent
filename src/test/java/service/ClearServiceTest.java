package service;

import dao.*;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.ClearResult;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ClearServiceTest {
    private User randUser;
    private User randUser2;
    private Person randPerson;
    private Person randPerson2;
    private Person randPerson3;
    private Event bestEvent;
    private Event bestEvent2;
    private Event bestEvent3;
    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();

        randUser = new User("user123", "password123", "bobtemp@email.com", "bob", "temp", "M", "person123");
        randUser2 = new User("user456", "password456", "mary@email.com", "mary", "temp", "F","person456");

        UserDAO uDao = new UserDAO(conn);
        uDao.clear();
        uDao.insert(randUser);
        uDao.insert(randUser2);

        randPerson = new Person("person123", "username123", "bob", "temp", "M");
        randPerson2 = new Person("person456", "username456", "mary", "temp", "F", "person123","", "");
        randPerson3 = new Person("person789", "username123", "joe", "temp", "M");

        PersonDAO pDao = new PersonDAO(conn);
        pDao.clear();
        pDao.insert(randPerson);
        pDao.insert(randPerson2);
        pDao.insert(randPerson3);

        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        bestEvent2 = new Event("Biking_123B", "Gabe", "Gabe123B",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        bestEvent3 = new Event("Biking_123C", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);

        EventDAO eDao = new EventDAO(conn);
        eDao.clear();
        eDao.insert(bestEvent);
        eDao.insert(bestEvent2);
        eDao.insert(bestEvent3);

        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void clearPass() throws DataAccessException {
        ClearService clearService = new ClearService();
        ClearResult clearResult = clearService.clear();

        assertTrue(clearResult.isSuccess());
    }

    @Test
    public void clearPass2() throws DataAccessException {
        ClearService service = new ClearService();
        ClearResult result = service.clear();
        assertTrue(result.isSuccess());
        result = service.clear();
        assertTrue(result.isSuccess());
    }
}