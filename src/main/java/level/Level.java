package level;

import board.Board;
import board.BoardFactory;
import ghosts.GhostFactory;

import java.util.List;

public class Level {

    private String mapPath;
    private Board board;
    private List ghosts;

    /**.
     * Creating a new level and its board and ghosts layout
     * @param lvlCount the difficulty of the level
     * @param bd wrapper for generating board
     * @param ghost wrapper for generating the ghosts with the respective level difficulty
     */
    public Level(int lvlCount, BoardFactory bd, GhostFactory ghost) {
        board = bd.createBoard(lvlCount);
        ghosts = ghost.createGhosts(lvlCount);
        mapPath = "resources/Level" + lvlCount + ".txt";
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
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
}
