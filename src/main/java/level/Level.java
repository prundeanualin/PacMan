package level;

import board.Board;
import ghosts.Ghost;

import java.util.List;

public class Level {

    private Board board;
    private List<Ghost> ghosts;

    /**.
     * Creating a new level with its parameters
     * @param bd the level's game board
     * @param ghost the ghosts from this level's layout
     */
    public Level(Board bd, List<Ghost> ghost) {
        board = bd;
        ghosts = ghost;
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
