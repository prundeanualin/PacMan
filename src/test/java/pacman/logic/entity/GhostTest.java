package pacman.logic.entity;

import org.junit.jupiter.api.Test;
import pacman.logic.Direction;
import pacman.logic.GameController;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;

import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {

    private final String map = "P.*#";
    private Board board;
    private Ghost ghost;

    @Test
    public void updatePacmanDies(){
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(1, 0));
        board.pacman.setAlive(true);
//        GameController.getInstance().getGame().setRunning(true);

//        ghost.setDirection(Direction.LEFT);
        board.pacman.setDirection(Direction.RIGHT);
        ghost.update(0.5);
        assertFalse(board.pacman.isAlive());
    }

    @Test
    public void updatePacmanAlive(){
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(2, 0));
        board.pacman.setAlive(true);
        board.pacman.setDirection(Direction.DOWN);
//        ghost.setDirection(Direction.RIGHT);
        ghost.update(0.5);
        assertTrue(board.pacman.isAlive());
    }

}
