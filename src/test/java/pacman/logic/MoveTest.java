package pacman.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for general movement.
 *
 * @param <Entity> The entity whose movement is tested.
 */
public abstract class MoveTest<Entity> {

    private enum Direction {} // Temporary Definition. TODO: remove.

    protected class Ghost {
    } // Temporary Definition. TODO: remove.

    protected class Player {
        // Temporary Definition. TODO: remove.
    }

    protected Ghost ghost;
    protected Player player;
    protected boolean MOVE_TO_GHOST; // Whether Entity can move to a ghost, and this causes game end.
    protected boolean MOVE_TO_PLAYER; // Whether Entity can move to a player, and this causes game end.

    /**
     * Sets up for the tests.
     * This includes ghost, player, MOVE_TO_GHOST and MOVE_TO_PLAYER.
     * Should not set the map, as this should be test case specific.
     */
    @BeforeEach
    public abstract void setup();

    /**
     * Try to move the {@code Entity} to an empty square/tile.
     * Assert he was moved moved and the game is NOT ended.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToEmpty(Direction direction) {
        fail();
    }

    /**
     * Try to move the ghost to a pellet.
     * Assert he was moved moved and the game is NOT ended.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToPellet(Direction direction) {
        fail();
    }

    /**
     * Try to move the ghost to a wall.
     * Assert he was NOT moved and the game is NOT ended.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToWall(Direction direction) {
        fail();
    }

    /**
     * Try to move the ghost to another ghost.
     * Assert he was (/NOT) moved and the game was (/NOT) ended iff (/NOT) MOVE_TO_GHOST.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToGhost(Direction direction) {
        fail();
    }

    /**
     * Try to move the ghost to the player.
     * Assert he was (/NOT) moved and the game was (/NOT) ended iff (/NOT) MOVE_TO_PLAYER.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToPlayer(Direction direction) {
        fail();
    }

    /**
     * Try to move the ghost to outside the Board/Map.
     * Assert he was (/NOT) moved and the game was (/NOT) ended.
     * Note: this is the trickiest test as it combines most/all of the other tests.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToOutside(Direction direction) {
        // Call each of the other tests in such a way wrapping occurs.
        // Do not forget to call setup after each test call.
        fail();
    }
}
