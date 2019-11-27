package pacman.logic.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;
import pacman.logic.level.MapParser;

import java.util.Iterator;

public class EntityTest {

    private PacMan pacMan;
    private Entity entity;

    @BeforeEach
    public void init() {
        Board board = new MapParser("").parseMapFromString("*P");
        Level level = new LevelFactory().createLevel(board);
        Iterator<Entity> iter = board.getEntities().iterator();
        entity = iter.next();
        if (!(entity instanceof Pellet)) entity = iter.next();
        pacMan = level.getPacMan();
    }

    @Test
    public void testUpdate() {
        entity.setDirection(Direction.RIGHT);
        entity.update(0.1);
        assertEquals(0.6, entity.getX(), 0.001);
    }

    @Test
    public void testCollision() {
        pacMan.setDirection(Direction.LEFT);
        pacMan.update(1);
        assertFalse(entity.collide(pacMan));
    }

    @Test
    public void testDistanceX() {
        assertEquals(0.1, entity.distanceX(0.6), 0.001);
    }

    @Test
    public void testDistanceXWrap() {
        assertEquals(0.6, entity.distanceX(1.9), 0.001);
    }

    @Test
    public void testSquare() {
        assertEquals(entity.getBoard().getSquare(0, 0), entity.getSquare());
    }

}
