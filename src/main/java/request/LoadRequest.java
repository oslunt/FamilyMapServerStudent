package request;

import java.util.ArrayList;

import model.Event;
import model.Person;
import model.User;

public class LoadRequest {

    /**
     * Users to be added
     */
    private ArrayList<User> users;

    /**
     * Persons to be added
     */
    private ArrayList<Person> persons;

    /**
     * Events to be added
     */
    private ArrayList<Event> events;

    /**
     * Creates the LoadRequest
     * @param users users to add to the database
     * @param persons persons to add to the database
     * @param events events to add to the database
     */
    public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
