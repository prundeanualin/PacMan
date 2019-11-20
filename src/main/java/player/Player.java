package player;

public class Player {

    private int id;
    private String username;
    private String password;
    private int score;

    /**.
     * Creating a player entity, same as the one stored in the db
     * @param un username
     * @param pw password
     */
    public Player(String un, String pw) {
        username = un;
        password = pw;
        score = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
