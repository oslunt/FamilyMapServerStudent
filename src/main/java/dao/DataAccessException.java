package dao;

/**
 * Database Exception handler
 */
public class DataAccessException extends Exception {

    /**
     * Returns exception
     * @param s message
     */
    public DataAccessException(String s) {
        super(s);
    }
}
