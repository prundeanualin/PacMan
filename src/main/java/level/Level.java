package level;

import board.Board;
import controllers.LevelController;
import ghosts.Ghost;
import ghosts.GhostFactory;

import java.util.List;

public class Level {

    private LevelController levelController;
    private Board board;
    private List<Ghost> ghosts;
    private int currentLevel;

    /**.
     * Creating a new level and its board and ghosts layout
     * @param lvlCount the difficulty of the level
     * @param ghost wrapper for generating the ghosts with the respective level difficulty
     */
    public Level(int lvlCount, Board bd, List<Ghost> ghost, LevelController lc) {
        board = bd;
        ghosts = ghost;
        currentLevel = lvlCount;
        levelController = lc;
    }

    public void showStartText() {
        levelController.showStartMessage();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List getGhosts() {
        return ghosts;
    }

    public void setGhosts(List ghosts) {
        this.ghosts = ghosts;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public LevelController getLevelController() {
        return levelController;
    }

    public void setLevelController(LevelController levelController) {
        this.levelController = levelController;
    }
}
