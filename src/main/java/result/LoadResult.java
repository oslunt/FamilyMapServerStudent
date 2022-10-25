package result;

/**
 * Result of LoadService
 */
public class LoadResult extends ParentResult {

    /**
     * Creates both successful and unsuccessful load results
     * @param message message string
     * @param success success of the service
     */
    public LoadResult(boolean success, String message) {
        super(success, message);
    }
}
