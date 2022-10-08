package result;

public class SingleEventResult {

    /**
     * The associated username with the event
     */
    private String associatedUsername;

    /**
     * The eventId of the event
     */
    private String eventID;

    /**
     * The associated personID of the event
     */
    private String personID;

    /**
     * The associated latitude of the event
     */
    private Float latitude;

    /**
     * The associated longitude of the event
     */
    private Float longitude;

    /**
     * The associated country of the event
     */
    private String country;

    /**
     * The associated city of the event
     */
    private String city;

    /**
     * The associated eventType of the event
     */
    private String eventType;

    /**
     * The associated year of the event
     */
    private Integer year;

    /**
     * The success of the service
     */
    private boolean success;

    /**
     * The error message of the service
     */
    private String message;

    /**
     * Creates a unsuccessful service result
     * @param success signifies the failure of the service
     * @param message error message of the service
     */
    public SingleEventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Creates a successful service result
     * @param success signifies the success of the service
     * @param associatedUsername associatedUsername of the event
     * @param eventID eventID of the event
     * @param personID personID of the event
     * @param latitude latitude of the event
     * @param longitude longitude of the event
     * @param country country of the event
     * @param city city of the event
     * @param eventType eventType of the event
     * @param year year of the event
     */
    public SingleEventResult(boolean success, String associatedUsername, String eventID, String personID, Float latitude, Float longitude, String country, String city, String eventType, Integer year) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEventType() {
        return eventType;
    }

    public Integer getYear() {
        return year;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
