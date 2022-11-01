package result;

/**
 * Result of SinglePersonService
 */
public class SinglePersonResult extends ParentResult {
    /**
     * The associated username with the person
     */
    private String associatedUsername;

    /**
     * The personId of the person
     */
    private String personID;

    /**
     * The associated firstName of the person
     */
    private String firstName;

    /**
     * The associated lastName of the person
     */
    private String lastName;

    /**
     * The associated gender of the person
     */
    private String gender;

    /**
     * The associated fatherID of the person
     */
    private String fatherID;

    /**
     * The associated motherID of the person
     */
    private String motherID;

    /**
     * The associated spouseID of the person
     */
    private String spouseID;


    /**
     * Creates a unsuccessful service result
     *
     * @param success signifies the failure of the service
     * @param message error message of the service
     */
    public SinglePersonResult(boolean success, String message) {
        super(success, message);
    }

    /**
     * Creates a successful service result
     *
     * @param success            signifies the success of the service
     * @param associatedUsername associatedUsername of the person
     * @param personID           personID of the person
     * @param firstName          firstName of the person
     * @param lastName           lastName of the person
     * @param gender             gender of the person
     * @param fatherID           fatherID of the person
     * @param motherID           motherID of the person
     * @param spouseID           spouseID of the person
     */
    public SinglePersonResult(boolean success, String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        super(success);
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}
