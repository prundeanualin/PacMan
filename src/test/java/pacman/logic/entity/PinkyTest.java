package pacman.logic.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;

public class PinkyTest {

    @Test
    public void basicTest() {
        String map = "....." +
                "P...." +
                "....p";
        Board board = MapParser.parseMapFromString(map);
        board.pacman.setDirection(Direction.RIGHT);
        Pinky p = (Pinky) board.ghosts.iterator().next();
        assertEquals(board.getSquare(4, 0), p.chooseTarget());
    }

    @Test
    public void shantedTest() {
        String map = "....p.." +
                "......." +
                "......P" +
                "......." +
                ".......";
        Board board = MapParser.parseMapFromString(map);
        board.pacman.setDirection(Direction.LEFT);
        Pinky p = (Pinky) board.ghosts.iterator().next();
        assertEquals(board.getSquare(0, 4), p.chooseTarget());
    }
}
