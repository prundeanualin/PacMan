package pacman.logic;

public class Player {

    private int score; //NOPMD instead of calling set, it seems more logical to have an updateScore method

    public Player() {

    }

    public int getScore() {
        return this.score;
    }

    public void updateScore(int score) {
        this.score += score;
    }
}
