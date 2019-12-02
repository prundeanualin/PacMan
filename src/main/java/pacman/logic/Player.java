package pacman.logic;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Player {

    private int score;
    private String username;

    public Player() {

    }

    public int getScore() {
        return this.score;
    }

    public void updateScore(int score) {
        this.score += score;
    }

    public void setUsername(String nm) {
        username = nm;
    }

}
