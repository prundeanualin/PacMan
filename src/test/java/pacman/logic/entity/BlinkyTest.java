package pacman.logic.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Not a bean.
public class BlinkyTest {
    private final String map = "P.*#";
    private Board board;
    private Blinky blinky;

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void chaseTargetTest(Direction pacDirection) {
        board = MapParser.parseMapFromString(map);
        board.pacman.setDirection(pacDirection);
        blinky = new Blinky(board, board.getSquare(1, 0));
        assertEquals(blinky.chaseTarget(), board.getSquare(0, 0));
    }

    @Test
    public void chaseTargetTestWithoutPacMan() {
        board = MapParser.parseMapFromString(map);
        board.removeEntity(board.pacman);
        blinky = new Blinky(board, board.getSquare(1, 0));
        assertNull(blinky.chaseTarget());
    }

}
