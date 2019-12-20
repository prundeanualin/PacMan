package pacman.logic.level;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class LevelTest {

    private LevelFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new LevelFactory();
    }

    @Test
    public void testLevelWon() {
        Level level = factory.createLevel(MapParser.parseMapFromString("P"));
        assertTrue(level.checkLevelWon());
    }

    @Test
    public void testLevelNotLost() {
        Level level = factory.createLevel(MapParser.parseMapFromString("P"));
        assertFalse(level.checkLevelLost());
    }

}
