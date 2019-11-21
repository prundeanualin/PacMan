package player;

/**.
 * Class for querying db and creating a new Player from query results.
 * Also, allows a user to log out from his account and log into another one
 */
public class AccountFactory {

    private String username;

    public Player createPlayer() {
        return new Player(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //TODO having the db connector and query procedure
    // for an username and creating a player from the results

}
