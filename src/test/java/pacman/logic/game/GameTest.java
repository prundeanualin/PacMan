package pacman.logic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.logic.Player;
import pacman.logic.entity.Entity;
import pacman.logic.level.Board;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;
import pacman.logic.level.MapParser;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class GameTest {

    private Game game;

    /**
     * setting up the testing environment.
     */
    @BeforeEach
    public void init() {
        Board board = new MapParser("").parseMapFromString("*P");
        Level level = new LevelFactory().createLevel(board);
        List<Level> levels = new ArrayList<>();
        levels.add(level);
        game = new Game(new Player(), levels);
    }

    @Test
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public void testUpdate() {
        for (Entity entity : game.getLevel().getBoard().getEntities()) {
            entity.setAlive(false);
        }
        game.setState(GameState.RUNNING);
        game.update(0);
        Iterator<Entity> iterator = game.getLevel().getBoard().getEntities().iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void testLoss() {
        game.setState(GameState.RUNNING);
        game.getPlayer().loseLife();
        game.getPlayer().loseLife();
        game.getPlayer().loseLife();
        game.update(0);
        assertEquals(GameState.LOST, game.getState().getValue());
    }

}