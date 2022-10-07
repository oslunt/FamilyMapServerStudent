package dao;

import model.Authtoken;
import model.Person;

import java.sql.*;

public class AuthtokenDAO {

    private final Connection conn;

    public AuthtokenDAO(Connection conn) {
        this.conn = conn;
    }

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
