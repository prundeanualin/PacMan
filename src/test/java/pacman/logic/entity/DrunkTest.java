package pacman.logic.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.MapParser;

@SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.BeanMembersShouldSerialize"})
//tests have some variables that fall into the PMD bugs spectrum (for loop & bean).
class DrunkTest {

    private Board board;
    private PacMan pacMan;

    private void init(String map) {
        board = MapParser.parseMapFromString(map);
        pacMan = board.getPacman();
    }

    @ParameterizedTest
    @CsvSource({"....P.,0", "..bP.,1", ".bbP.,2"})
    public void createBottle(String map, int expected) {
        init(map);
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
        init("#bP");
        Assertions.assertFalse(pacMan.isDrunk());

        pacMan.setNextDirection(Direction.LEFT);
        pacMan.update(0.5);
        Assertions.assertTrue(pacMan.isDrunk());
    }

    @Test
    public void becomesUnDrunk() {
        init("#.Pb..................");
        pacMan.setNextDirection(Direction.RIGHT);
        pacMan.update(0.5);
        Assertions.assertTrue(pacMan.isDrunk());
        //! Dont run into walls when updating with > 1 second !!
        pacMan.update(PacMan.drunkTime - 1e-10);
        Assertions.assertTrue(pacMan.isDrunk());

        pacMan.update(2e-10);
        Assertions.assertFalse(pacMan.isDrunk());
    }
}