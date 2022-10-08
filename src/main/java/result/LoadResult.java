package result;

public class LoadResult {

    /**
     * Message of the operation
     */
    private String message;

    /**
     * Success of the operation
     */
    private boolean success;

    /**
     * Creates both successful and unsuccessful load results
     * @param message message string
     * @param success success of the service
     */
    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
