package dao;

import model.Event;
import model.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * Event Data Access Object
 */
public class EventDAO {

    /**
     * Connection to the database
     */
    private final Connection conn;

    /**
     * Sets connection to the database
     * @param conn connection to the database
     */
    public EventDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gets everything in the event table
     * @return everything in the event table in an array list
     * @throws DataAccessException
     */
    public ArrayList<Event> returnAll() throws DataAccessException {
        ArrayList<Event> events = new ArrayList<Event>();
        String sql = "SELECT * FROM Event";
        ResultSet rs;
        try (PreparedStatement stmt = conn.prepareStatement((sql))) {
            rs = stmt.executeQuery();
            if (rs.next()) {
                events.add(new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year")));
                while(rs.next()) {
                    events.add(new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                            rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                            rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                            rs.getInt("year")));
                }
                return events;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all Events in the database");
        }
    }

    /**
     * Inserts into the event table
     * @param event event that will be inserted
     * @throws DataAccessException
     */
    public void insert(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Event (eventID, associatedUsername, personID, latitude, longitude, " +
                "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * Finds a specific event
     * @param eventID the eventID string to find
     * @return The full event found
     * @throws DataAccessException
     */
    public Event find(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Event WHERE eventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }

    /**
     * Deletes everything from the datatable
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Event";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    public void clearUser(String username) throws DataAccessException {
        String sql = "DELETE FROM Event WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing an username in the database");
        }
    }

    public ArrayList<Event> findFamilyEvents(String username) throws DataAccessException {
        ArrayList<Event> events = new ArrayList<Event>();
        String sql = "SELECT * FROM Event WHERE associatedUsername = ?";
        ResultSet rs;
        try (PreparedStatement stmt = conn.prepareStatement((sql))) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                events.add(new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year")));
                while(rs.next()) {
                    events.add(new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                            rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                            rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                            rs.getInt("year")));
                }
                return events;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all Events in the database");
        }
    }
}