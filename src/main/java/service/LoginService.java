package service;

import dao.*;
import model.Authtoken;
import model.User;
import request.LoginRequest;
import result.LoginResult;

import java.sql.Connection;
import java.util.UUID;

/**
 * Logs the user in
 */
public class LoginService {

    /**
     * Logs the user in
     * @param l the login request parameters
     * @return Returns an authtoken in LoginResult
     */
    public LoginResult login(LoginRequest l) {
        Database db = new Database();
        Connection conn = null;
        try {
            conn = db.openConnection();

            UserDAO uDAO = new UserDAO(conn);
            User u = uDAO.find(l.getUsername());
            if(u != null && u.getPassword().equals(l.getPassword())) {
                String authToken = UUID.randomUUID().toString();
                AuthtokenDAO aDao = new AuthtokenDAO(conn);
                aDao.insert(new Authtoken(authToken, l.getUsername()));
                db.closeConnection(true);
                return new LoginResult(true, authToken, l.getUsername(), u.getPersonID());
            }
            else {
                db.closeConnection(true);
                return new LoginResult(false, "Error: Incorrect username or password");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
