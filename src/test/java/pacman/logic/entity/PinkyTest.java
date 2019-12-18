package pacman.logic.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;

public class PinkyTest {
    private final String map = ".........\n" + // NOPMD string repetition necessary
            ".........\n" +
            ".........\n" +
            ".........\n" +
            "....Pp...\n" +
            ".........\n" +
            ".........\n" +
            ".........\n" +
            ".........\n";

    @ParameterizedTest
    @CsvSource({"UP, 4, 0", "RIGHT, 8, 4", "DOWN, 4, 8", "LEFT, 0, 4"})
    public void chaseTargetTest(Direction pacmanDirection, int x, int y) {
        Board board = MapParser.parseMapFromString(map);
        board.pacman.setDirection(pacmanDirection);
        Pinky p = (Pinky) board.ghosts.iterator().next();
        assertEquals(board.getSquare(x, y), p.chaseTarget());
    }

    @Test
    public void chaseTargetTestWithoutPacMan() {
        Board board = MapParser.parseMapFromString(map);
        board.removeEntity(board.pacman);
        Pinky p = (Pinky) board.ghosts.iterator().next();
        assertNull(p.chaseTarget());
    }
}
