package pacman.logic.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.logic.level.Board;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;
import pacman.logic.level.MapParser;

class WallTest {

    private Board board;

    @BeforeEach
    public void init() {
        board = new MapParser(".").parseMapFromString("#P");
        Level level = new LevelFactory().createLevel(board);
    }

    @Test
    public void createWall() {
        Wall wall = null;
        for(Entity e: board.getEntities()) {
            if (e instanceof Wall)
                wall =(Wall) e;
        }
        Assertions.assertNotNull(wall);
    }
}