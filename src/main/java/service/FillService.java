package service;

import dao.*;
import model.Person;
import model.User;
import request.FillRequest;
import result.FillResult;

import java.sql.Connection;
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

            PersonDAO pDao = new PersonDAO(conn);
            EventDAO eDao = new EventDAO(conn);

            pDao.clearUser(f.getUsername());
            eDao.clearUser(f.getUsername());

            User u = uDao.find(f.getUsername());

            //generate(0, f.getGenerations(), f.getUsername(), u.getFirstName(), u.getLastName(), u.getGender(), pDao, eDao);


            db.closeConnection(true);
            return null;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public String[] fNames = {"James", "Robert", "John", "Michael", "David", "William", "Richard", "Joseph", "Thomas", "Charles", "Christopher", "Daniel"};
    public String[] lNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez" ,"Hernandez", "Lopez"};
    /*public void generate(int curGen, int desGen, String username, String fName, String lName, String gender, PersonDAO p, EventDAO e) {
        if(curGen < desGen) {
            int rnd = new Random().nextInt(fNames.length);
            String fatherFName = fNames[rnd];
            String lastName = lNames[rnd];
            p.insert(new Person(fName + "_" + lName, username, fName, lName, gender, fatherFName + "_" + lastName, ));
        }
    }*/
}
