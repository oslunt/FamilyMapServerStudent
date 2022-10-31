package service;

import dao.*;
import model.Event;
import model.Person;
import model.User;
import request.FillRequest;
import request.LoadRequest;
import result.FillResult;
import result.LoadResult;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

/**
 * Populates the server's database with generated data for the specified username.
 * The required "username" parameter must be a user already registered with the server.
 * If there is any data in the database already associated with the given username, it is deleted.
 * The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated,
 * and must be a non-negative integer (the default is 4, which results in 31 new persons each with associated events).
 */
public class FillService {

    /**
     * Populates the server's database with generated data for the specified username.
     * The required "username" parameter must be a user already registered with the server.
     * If there is any data in the database already associated with the given username, it is deleted.
     * The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated,
     * and must be a non-negative integer (the default is 4, which results in 31 new persons each with associated events).
     * @param f the username and generations to generate it for
     * @return The success or failure of the operation
     */
    public FillResult fill(FillRequest f) {
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();
            UserDAO uDao = new UserDAO(conn);
            User u = uDao.find(f.getUsername());
            
            PersonDAO pDao = new PersonDAO(conn);
            pDao.clearUser(f.getUsername());


            EventDAO eDao = new EventDAO(conn);
            eDao.clearUser(f.getUsername());

            db.closeConnection(true);

            ArrayList<Person> persons = new ArrayList<>();
            ArrayList<Event> events = new ArrayList<>();

            String[] fNames = {"James", "Robert", "John", "Michael", "David", "William", "Richard", "Joseph", "Thomas", "Charles", "Christopher", "Daniel"};
            String[] lNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez" ,"Hernandez", "Lopez"};
            String[] femNames = {"Mary", "Patricia", "Jennifer", "Linda", "Elizabeth", "Barbara", "Susan", "Jessica", "Sarah", "Karen", "Lisa", "Nancy"};

            generate(0, f.getGenerations(), f.getUsername(), u.getFirstName(), u.getLastName(), u.getGender(), persons, events, "", fNames, lNames, femNames);

            LoadService ls = new LoadService();
            LoadResult lr = ls.load(new LoadRequest(null, persons, events));
            if(lr.isSuccess()) {
                return new FillResult(true, "Successfully added user's family tree");
            }
            else {
                return new FillResult(false, "Was not able to add user's family tree");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public void generate(int curGen, int desGen, String username, String fName, String lName, String gender, ArrayList<Person> p, ArrayList<Event> e, String spouse, String[] fNames, String[] lNames, String[] femNames) {
        if(curGen <= desGen) {
            int rnd = new Random().nextInt(fNames.length);
            String fatherFName = fNames[rnd];
            String motherFName = femNames[rnd];
            String lastName = lNames[rnd];
            p.add(new Person(fName + "_" + lName, username, fName, lName, gender, fatherFName + "_" + lastName, motherFName + "_" + lastName, spouse));
            generate(curGen + 1, desGen, username, fatherFName, lastName, "m", p, e, motherFName + "_" + lastName, fNames, lNames, femNames);
            generate(curGen + 1, desGen, username, motherFName, lastName, "f", p, e, fatherFName + "_" + lastName, fNames, lNames, femNames);
            e.add(new Event(fName + "_" +lName + "_Birth", username, fName + "_" + lName, (float)34.0522, (float)118.2437, "USA", "Los Angeles", "Birthday", 2000 - (30*curGen)));
            e.add(new Event(fName + "_" +lName + "_Marriage", username, fName + "_" + lName, (float)34.0522, (float)118.2437, "USA", "Los Angeles", "Marriage", 2000 - (30 * curGen - 25)));
            e.add(new Event(fName + "_" +lName + "_Death", username, fName + "_" + lName, (float)34.0522, (float)118.2437, "USA", "Los Angeles", "Death", 2000 - (30 * curGen - 80)));

        }
    }
}
