package pacman.logic.level;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.logic.entity.Entity;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;

class BoardTest {

    Board board;
    Level level;
    Entity entity;

    @BeforeEach
    public void init() {
        board = new MapParser(".").parseMapFromString("##P.#");
        level = new LevelFactory().createLevel(board);
    }

    @Test
    void getSquare() {
        Square square = board.getSquare(1, 0);
        assertNotNull(square);
    }

    @Test
    void addSquare() {
        entity = new Pellet(board, 0, 0);
        board.addSquare(new Square(entity));
        int a = 0;
        assertEquals(6, StreamSupport.stream(board.getSquares().spliterator(), false).count());
    }

    @Test
    void removeEntity() {
        for(Entity e: board.getEntities()) {
            if(e instanceof PacMan)
                entity = e;
        }
        Square sq = entity.getSquare();
        board.removeEntity(entity);
        assertEquals(0, StreamSupport.stream(sq.getEntities().spliterator(), false).count());
        assertTrue(StreamSupport.stream(board.getEntities().spliterator(), false).noneMatch(a -> a instanceof PacMan));
    }

    @Test
    void getEntities() {
        assertEquals(4, StreamSupport.stream(board.getEntities().spliterator(), false).count());
    }

    @Test
    void getSquares() {
        assertEquals(5, StreamSupport.stream(board.getSquares().spliterator(), false).count());
    }

    @Test
    void checkLevelWon() {
        for(Entity e: board.getEntities()) {
            if(e instanceof Pellet)
                e.setAlive(false);
        }
        assertTrue(StreamSupport.stream(board.getEntities().spliterator(), false).noneMatch(e -> e instanceof Pellet && e.isAlive()));
        board.addSquare(new Square(new Pellet(board, 0, 0)));
        assertFalse(StreamSupport.stream(board.getEntities().spliterator(), false).noneMatch(e -> e instanceof Pellet && e.isAlive()));
    }

    @Test
    void computeScore() {
        Pellet p = new Pellet(board, 0, 0);
        p.setAlive(false);
        board.addSquare(new Square(p));
        assertEquals(1, board.computeScore());
    }

    @Test
    void removeDeadEntities() {
        Pellet p = new Pellet(board, 0, 0);
        board.addSquare(new Square(p));
        assertTrue(StreamSupport.stream(board.getEntities().spliterator(), false).anyMatch(e -> e instanceof Pellet));
        board.removeEntity(p);
        assertFalse(StreamSupport.stream(board.getEntities().spliterator(), false).anyMatch(e -> e instanceof Pellet));
    }
}