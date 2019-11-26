package pacman.logic;

public class Player {

    private int score;

    public Player() {

    }

    public int getScore() {
        return this.score;
    }

    public void updateScore(int score) {
        this.score += score;
    }
}
