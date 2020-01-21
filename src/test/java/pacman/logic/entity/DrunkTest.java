package pacman.logic.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;
import pacman.logic.level.MapParser;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
//tests have some variables that fall into the PMD bugs spectrum
class DrunkTest {

    private Board board;
    private PacMan pacMan;

    private void init(String map) {
        board = MapParser.parseMapFromString(map);
    }

    @ParameterizedTest
    @CsvSource({"....P.,0", "..BP.,1", ".BBP.,2"})
    public void createBottle(String map, int expected) {
        int bottles = 0;
        for (Entity e : board.getEntities()) {
            if (e instanceof Bottle) {
                bottles++;
            }
        }
        Assertions.assertEquals(expected, bottles);
    }

    @Test
    public void becomesDrunk() {
        init("#BP");
        Assertions.assertFalse(pacMan.isDrunk());

        pacMan.setNextDirection(Direction.LEFT);
        pacMan.update(0.5);
        Assertions.assertTrue(pacMan.isDrunk());
    }

    @Test
    public void becomesUnDrunk() {
        becomesDrunk();
        pacMan.update(PacMan.drunkTime - Double.MIN_VALUE);
        Assertions.assertTrue(pacMan.isDrunk());

        pacMan.update(Double.MIN_VALUE);
        Assertions.assertFalse(pacMan.isDrunk());
    }
}