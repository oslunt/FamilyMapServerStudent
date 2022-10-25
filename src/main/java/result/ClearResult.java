package result;

/**
 * Result of ClearService
 */
public class ClearResult extends ParentResult {


    /**
     * Creates both successful and unsuccessful load results
     * @param message message string
     * @param success success of the service
     */
    public ClearResult(boolean success, String message) {
        super(success, message);
    }
}
