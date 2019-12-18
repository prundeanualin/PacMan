package pacman.logic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import pacman.graphics.BoardCanvas;
import pacman.logic.Player;
import pacman.logic.level.Board;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;
import pacman.logic.level.MapParser;

@RunWith(PowerMockRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameControllerTest {

    @Test
    @Order(1)
    public void testCreateController() {
        assertNotNull(GameController.getInstance());
    }

    @Test
    @Order(2)
    public void testOneInstance() {
        assertEquals(GameController.getInstance(), GameController.getInstance());
    }

    @Test
    @Order(3)
    public void testPauseNotStarted() {
        assertThrows(IllegalStateException.class, () -> GameController.getInstance().pause());
    }


    @Test
    @Order(3)
    public void testUnpauseNotStarted() {
        assertThrows(IllegalStateException.class, () -> GameController.getInstance().unpause());
    }

    @Test()
    @Order(4)
    public void testStartAndClose() {
        GameController.getInstance().setTimer(prepareTimer());
        GameController.getInstance().start();
        assertTrue(GameController.getInstance().getGame().isRunning());
        GameController.getInstance().pause();
        assertFalse(GameController.getInstance().getGame().isRunning());
    }

    @Test()
    @Order(5)
    public void testGameWon() {
        Board bd = new MapParser(".").parseMapFromString("##P..#");
        Level lvl = new LevelFactory().createLevel(bd);
        List<Level> lv = new ArrayList<>();
        lv.add(lvl);
        BoardCanvas cnv = mock(BoardCanvas.class);
        doNothing().when(cnv).draw(anyDouble());
        GameController.getInstance().setGame(new Game(new Player(), lv));
        GameController.getInstance().setTimer(prepareTimer());
        GameController.getInstance().start();
        GameController.getInstance().update(3);
        assertTrue(GameController.getInstance().getGame().won(0));
    }

    /**
     * prepares the mock for the animationtimer.
     * @return that animationtimer mocked.
     */
    public AnimationTimer prepareTimer() {
        AnimationTimer at = mock(AnimationTimer.class);
        doNothing().when(at).start();
        doNothing().when(at).stop();
        return at;
    }

}
