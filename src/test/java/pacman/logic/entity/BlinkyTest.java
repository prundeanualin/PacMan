package pacman.logic.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;

public class BlinkyTest {
    Board board;
    Blinky blinky;

    @Test
    public void TestWithPacMan() {
        Board board = MapParser.parseMapFromString("P.*#");
        Blinky blinky = new Blinky(board, board.getSquare(1, 0));
        assertEquals(blinky.chooseTarget(), board.getSquare(0, 0));
    }

    @Test
    public void testWithoutPacMan() {
        Board board = MapParser.parseMapFromString("P.*#");
        board.removeEntity(board.pacman);
        Blinky blinky = new Blinky(board, board.getSquare(1, 0));
        assertNull(blinky.chooseTarget());
    }

}
