package service;

import dao.*;
import model.Authtoken;
import model.User;
import request.FillRequest;
import request.RegisterRequest;
import result.MultiPersonResult;
import result.RegisterResult;

import java.sql.Connection;
import java.util.UUID;

/**
 * Creates a new user account (user row in the database)
 * Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
 * Logs the user in
 */
public class RegisterService {

    /**
     * Creates a new user account (user row in the database)
     * Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
     * Logs the user in
     * @param r the parameters needed for request
     * @return Returns an authtoken
     */
    public RegisterResult register(RegisterRequest r) {
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();

            UserDAO uDao = new UserDAO(conn);
            if(uDao.find(r.getUsername()) == null) {
                User u = new User(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLastName(), r.getGender(), r.getFirstName() + "_" + r.getLastName());
                uDao.insert(u);

                String authToken = UUID.randomUUID().toString();
                AuthtokenDAO aDao = new AuthtokenDAO(conn);
                aDao.insert(new Authtoken(authToken, r.getUsername()));

                db.closeConnection(true);

                //FillService fs = new FillService();
                //fs.fill(new FillRequest(r.getUsername()));

                return new RegisterResult(true, authToken, r.getUsername(), r.getFirstName() + "_" + r.getLastName());
            }
            else {
                db.closeConnection(true);
                return  new RegisterResult(false, "Username already exists");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
