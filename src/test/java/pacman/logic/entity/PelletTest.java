package pacman.logic.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.logic.level.Board;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;
import pacman.logic.level.MapParser;

class PelletTest {

    private Board board;

    @BeforeEach
    public void init() {
        MapParser mapParser = new MapParser(".");
        board = mapParser.parseMapFromString("##*P#");
        Level level = new LevelFactory().createLevel(board);
    }

    @Test
    public void createPellet() {
        Pellet pellet = null;
        for(Entity e: board.getEntities()) {
            if(e instanceof Pellet)
                pellet = (Pellet) e;
            System.out.println(e.getX());
        }
        Assertions.assertNotNull(pellet);
    }

}