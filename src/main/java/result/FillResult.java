package result;

/**
 * Result of FillService
 */
public class FillResult extends ParentResult {

    /**
     * Creates both successful and unsuccessful load results
     * @param message message string
     * @param success success of the service
     */
    public FillResult(boolean success, String message) {
        super(success, message);
    }

}
