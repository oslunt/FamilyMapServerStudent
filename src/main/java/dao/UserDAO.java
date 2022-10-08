package dao;

import model.User;
import model.Event;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    /**
     * Connection to the database
     */
    private final Connection conn;

    /**
     * Sets connection to the database
     * @param conn connection to the database
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gets everything in the user table
     * @return everything in the user table in an array list
     * @throws DataAccessException
     */
    public ArrayList<User> returnAll() throws DataAccessException {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM User";
        ResultSet rs;
        try (PreparedStatement stmt = conn.prepareStatement((sql))) {
            rs = stmt.executeQuery();
            if (rs.next()) {
                users.add(new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID")));
                while(rs.next()) {
                    users.add(new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID")));
                }
                return users;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all Users in the database");
        }
    }
    
    /**
     * Inserts into the user table
     * @param user user that will be inserted
     * @throws DataAccessException
     */
    public void insert(User user) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO User (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an user into the database");
        }
    }

    /**
     * Finds a specific user
     * @param username the username string to find
     * @return The full user found
     * @throws DataAccessException
     */
    public User find(String username) throws DataAccessException {
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an user in the database");
        }

    }

    /**
     * Deletes everything from the datatable
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM User";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }
}
