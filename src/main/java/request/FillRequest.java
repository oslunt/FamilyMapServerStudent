package request;

public class FillRequest {

    /**
     * Username for fill request
     */
    private String username;

    /**
     * Generations to generate
     */
    private Integer generations;

    /**
     * Constructor when generations is declared
     * @param username username for fill request
     * @param generations generations to generate
     */
    public FillRequest(String username, Integer generations) {
        this.username = username;
        this.generations = generations;
    }

    /**
     * Default constructor, default generations of 4
     * @param username username for fill request
     */
    public FillRequest(String username) {
        this.username = username;
        this.generations = 4;
    }

    public String getUsername() {
        return username;
    }

    public Integer getGenerations() {
        return generations;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGenerations(Integer generations) {
        this.generations = generations;
    }
}
