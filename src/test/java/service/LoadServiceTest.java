package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import result.LoadResult;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoadServiceTest {

    @BeforeEach
    public void setUp() throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();

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
    public void loadPass() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();

        users.add(new User("user123", "password123", "bobtemp@email.com", "bob", "temp", "M", "bob_temp"));
        users.add(new User("user456", "password456", "mary@email.com", "mary", "temp", "F","person456"));

        persons.add(new Person("person123", "username123", "bob", "temp", "M"));
        persons.add(new Person("person456", "username456", "mary", "temp", "F", "person123","", ""));
        persons.add(new Person("person789", "username123", "joe", "temp", "M"));

        events.add(new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016));
        events.add(new Event("Biking_123B", "Gabe", "Gabe123B",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016));
        events.add(new Event("Biking_123C", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016));

        LoadService service = new LoadService();
        LoadResult result = service.load(new LoadRequest(users, persons, events));

        String[] message = result.getMessage().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        Assertions.assertEquals("Successfully added ", message[0]);
        Assertions.assertEquals(users.size(), Integer.parseInt(message[1]));
        Assertions.assertEquals(" users, ", message[2]);
        Assertions.assertEquals(persons.size(), Integer.parseInt(message[3]));
        Assertions.assertEquals(" persons, and ", message[4]);
        Assertions.assertEquals(events.size(), Integer.parseInt(message[5]));
    }

    @Test
    public void loadFail() {
        ArrayList<User> users = null;
        ArrayList<Person> persons = null;
        ArrayList<Event> events = null;

        LoadService service = new LoadService();
        LoadResult result = service.load(new LoadRequest(users, persons, events));

        assertFalse(result.isSuccess());
    }
}