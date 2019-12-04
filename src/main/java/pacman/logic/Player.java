package pacman.logic;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Player {

    private int score;
    private String username;
    private int lives = 3;

    public Player() {

    }

    public int getLives() {
        return lives;
    }

    public boolean hasLives() {
        return lives > 0;
    }

    public void loseLife() {
        lives--;
    }

    public int getScore() {
        return this.score;
    }

    public void updateScore(int score) {
        this.score += score;
    }

    public void setUsername(String nm) {username = nm;}

}
