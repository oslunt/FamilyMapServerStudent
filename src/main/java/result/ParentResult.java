package result;

public abstract class ParentResult {

    private String message;


    private boolean success;


    public ParentResult(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public ParentResult(boolean success) {
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
