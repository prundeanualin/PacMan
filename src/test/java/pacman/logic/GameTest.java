package pacman.logic;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.logic.entity.Entity;
import pacman.logic.entity.Pellet;
import pacman.logic.level.Board;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;
import pacman.logic.level.MapParser;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class GameTest {

    private Game game;

    @BeforeEach
    public void init() {
        Board board = new MapParser("").parseMapFromString("*P");
        Level level = new LevelFactory().createLevel(board);
        List<Level> levels = new ArrayList<>();
        levels.add(level);
        game = new Game(new Player(), levels);
    }

    @Test
    public void testUpdate() {
        for (Entity entity : game.getLevel().getBoard().getEntities()) { // NOPMD foreach loop bug
            entity.setAlive(false);
        }
        game.setRunning(true);
        game.update(0);
        Iterator<Entity> iterator = game.getLevel().getBoard().getEntities().iterator();
        game.getLevel().getBoard().getEntities().forEach(System.out::println);
        assertFalse(iterator.hasNext());
    }

}
