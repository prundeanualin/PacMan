package pacman.logic.level;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.logic.entity.PacMan;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class LevelFactoryTest {

    private LevelFactory levelFactory;
    private Board board;

    @BeforeEach
    public void init() {
        levelFactory = new LevelFactory();
        board = new Board(10, 10);
    }

    @Test
    public void testNoPacMan() {
        assertThrows(IllegalArgumentException.class, () -> levelFactory.createLevel(board));
    }

    @Test
    public void testMultiplePacMan() {
        Square square = new Square(board, 0, 0);
        square.addEntity(new PacMan(board, square));
        square.addEntity(new PacMan(board, square));
        board.addSquare(square);
        assertThrows(IllegalArgumentException.class, () -> levelFactory.createLevel(board));
    }

    @Test
    public void testCreateLevelSuccess() {
        Square square = new Square(board, 0, 0);
        square.addEntity(new PacMan(board, square));
        board.addSquare(square);
        assertNotNull(levelFactory.createLevel(board));
    }

}
