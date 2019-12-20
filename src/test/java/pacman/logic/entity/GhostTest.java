package pacman.logic.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;
import pacman.logic.level.Square;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class GhostTest {

    private final String map = "P.*#";
    private Board board;
    private Ghost ghost;

    @Test
    public void updatePacmanDies() {
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(1, 0));
        board.pacman.setAlive(true);
        // GameController.getInstance().getGame().setRunning(true);

        // ghost.setDirection(Direction.LEFT);
        board.pacman.setDirection(Direction.RIGHT);
        ghost.update(0.5);
        assertFalse(board.pacman.isAlive());
    }

    @Test
    public void updatePacmanAlive() {
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(2, 0));
        board.pacman.setAlive(true);
        board.pacman.setDirection(Direction.DOWN);
        // ghost.setDirection(Direction.RIGHT);
        ghost.update(0.5);
        assertTrue(board.pacman.isAlive());
    }

    @Test
    public void ghostDoesntMove() {
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(1,0));
        ghost.update(0.0);
        assertEquals(ghost.oldSquare, ghost.square);
    }

    @Test
    public void noTarget() {
        String mapp = "..*#";
        board = MapParser.parseMapFromString(mapp);
        ghost = new Blinky(board, board.getSquare(1,0));
        ghost.update(0.5);
        assertTrue(ghost.oldSquare.equals(ghost.square));
    }

    @Test
    public void noOptions() {
        String map = ".";
        board = MapParser.parseMapFromString(map);
        ghost = new Blinky(board, board.getSquare(0,0));
        ghost.update(0);
        assertTrue(ghost.oldSquare.equals(ghost.square));
    }

    /**
     * Test to get the square options the ghost can move to.
     * The neighbors don't consist of solids(walls).
     */
    @Test
    public void getOptionsNoWalls() {
        String mapp = "P...*#";
        board = MapParser.parseMapFromString(mapp);
        ghost = new Blinky(board, board.getSquare(2,0));
        List<Square> options = new ArrayList<>();
        options.add(board.getSquare(1,0));
        options.add(board.getSquare(3,0));
        assertEquals(2, ghost.getOptions().size());
        assertEquals(options, ghost.getOptions());
    }

    @Test
    public void getOptionsWithWalls() {
        String mapp = ".#.\n"
                + "P.#\n"
                + "...";
        board = MapParser.parseMapFromString(mapp);
        ghost = new Blinky(board, board.getSquare(1,1));
        ghost.setDirection(Direction.RIGHT);
        List<Square> expected = new ArrayList<>();
        expected.add(board.getSquare(1,2));
        expected.add(board.getSquare(0,1));
        assertTrue(ghost.getOptions().containsAll(expected));
        assertEquals(2, ghost.getOptions().size());
    }

    @Test
    public void closestNeighborThrowsException() {
        String mapp = "P.";
        board = MapParser.parseMapFromString(mapp);
        ghost = new Blinky(board, board.getSquare(1,0));
        List<Square> expected = new ArrayList<>(); //NOPMD variable needed for testing purposes
        assertThrows(IllegalArgumentException.class, () ->
                ghost.closestNeighbour(board.pacman.square, expected));
    }

    @Test
    public void closestNeighbor() {
        String mapp = "P..*#";
        board = MapParser.parseMapFromString(mapp);
        ghost = new Blinky(board, board.getSquare(2,0));
        List<Square> options = new ArrayList<>();
        options.add(board.getSquare(1,0));
        options.add(board.getSquare(3,0));
        assertEquals(board.getSquare(1,0), ghost.closestNeighbour(board.pacman.square, options));
    }
}
