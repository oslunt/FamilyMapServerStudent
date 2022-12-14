package dao;

import model.Event;
import model.Person;
import model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Person Data Access Object
 */
public class PersonDAO {

    /**
     * Connection to the database
     */
    private final Connection conn;

    /**
     * Sets connection to the database
     * @param conn connection to the database
     */
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gets everything in the person table
     * @return everything in the person table in an array list
     * @throws DataAccessException
     */
    public ArrayList<Person> returnAll() throws DataAccessException {
        ArrayList<Person> persons = new ArrayList<Person>();
        String sql = "SELECT * FROM Person";
        ResultSet rs;
        try (PreparedStatement stmt = conn.prepareStatement((sql))) {
            rs = stmt.executeQuery();
            if (rs.next()) {
                persons.add(new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID")));
                while(rs.next()) {
                    persons.add(new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                            rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                            rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID")));
                }
                return persons;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all Persons in the database");
        }
    }

    /**
     * Inserts into the person table
     * @param person person that will be inserted
     * @throws DataAccessException
     */
    public void insert(Person person) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }
    }

    /**
     * Finds a specific person
     * @param personID the personID string to find
     * @return The full person found
     * @throws DataAccessException
     */
    public Person find(String personID) throws DataAccessException {
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an person in the database");
        }

    }

    /**
     * Deletes everything from the datatable
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Person";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    public void clearUser(String username) throws DataAccessException {
        String sql = "DELETE FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing an username in the database");
        }
    }

    public ArrayList<Person> findFamilyPersons(String username) throws DataAccessException {
        ArrayList<Person> persons = new ArrayList<Person>();
        String sql = "SELECT * FROM Person Where associatedUsername = ?";
        ResultSet rs;
        try (PreparedStatement stmt = conn.prepareStatement((sql))) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                persons.add(new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID")));
                while(rs.next()) {
                    persons.add(new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                            rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                            rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID")));
                }
                return persons;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all Persons in the database");
        }
    }
}
