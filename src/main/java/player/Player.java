package player;

public class Player {

    private String username;
    private int score;

    /**.
     * Creating a player entity, same as the one stored in the db
     * @param un username
     */
    public Player(String un) {
        username = un;
        score = 0;
    }

    public Player(String usern,int sc) {
        username = usern;
        score = sc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
