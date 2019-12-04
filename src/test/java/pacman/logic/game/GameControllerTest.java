package pacman.logic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pacman.logic.game.GameController;

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

}
