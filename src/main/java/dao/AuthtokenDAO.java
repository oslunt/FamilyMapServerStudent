package dao;

import model.Authtoken;
import model.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * Authtoken Data Access Object
 */
public class AuthtokenDAO {

    /**
     * Connection to the database
     */
    private final Connection conn;

    /**
     * Sets connection to the database
     * @param conn connection to the database
     */
    public AuthtokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gets everything in the authtoken table
     * @return everything in the authtoken table in an array list
     * @throws DataAccessException
     */
    public ArrayList<Authtoken> returnAll() throws DataAccessException {
        ArrayList<Authtoken> authtokens = new ArrayList<Authtoken>();
        String sql = "SELECT * FROM Authtoken";
        ResultSet rs;
        try (PreparedStatement stmt = conn.prepareStatement((sql))) {
            rs = stmt.executeQuery();
            if (rs.next()) {
                authtokens.add(new Authtoken(rs.getString("authtoken"), rs.getString("username")));
                while(rs.next()) {
                    authtokens.add(new Authtoken(rs.getString("authtoken"), rs.getString("username")));
                }
                return authtokens;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all authtokens in the database");
        }
    }

    /**
     * Inserts into the authtoken table
     * @param authtoken authtoken that will be inserted
     * @throws DataAccessException
     */
    public void insert(Authtoken authtoken) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an authtoken into the database");
        }
    }

    /**
     * Finds a specific authtoken
     * @param authtoken the authtoken string to find
     * @return The full authtoken found
     * @throws DataAccessException
     */
    public Authtoken find(String authtoken) throws DataAccessException {
        Authtoken authtoken1;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authtoken1 = new Authtoken(rs.getString("authtoken"), rs.getString("username"));
                return authtoken1;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an authtoken in the database");
        }

    }

    /**
     * Deletes everything from the datatable
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Authtoken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }
}
