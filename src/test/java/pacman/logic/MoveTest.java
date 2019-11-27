package pacman.logic;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pacman.logic.entity.Ghost;
import pacman.logic.entity.PacMan;

/**
 * Test class for general movement.
 *
 * @param <E> The entity whose movement is tested.
 */
public abstract class MoveTest<E> {

    protected Ghost ghost;
    protected PacMan pacMan;
    protected boolean isGhost; // Whether or not this is a ghost.

    /**
     * Sets up for the tests.
     * This includes ghost, player and isGhost.
     * Should not set the map, as this should be test case specific.
     */
    @BeforeEach
    public abstract void setUp();

    /**
     * Try to move the entity to an empty square/tile.
     * Assert entity was moved moved and the game is NOT ended.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToEmpty(Direction direction) {
        fail();
    }

    /**
     * Try to move the entity to a pellet.
     * Assert entity was moved moved and the game is NOT ended.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToPellet(Direction direction) {
        fail();
    }

    /**
     * Try to move the entity to a wall.
     * Assert entity was NOT moved and the game is NOT ended.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToWall(Direction direction) {
        fail();
    }

    /**
     * Try to move the entity to a ghost.
     * Assert entity was moved and the game was ended iff the entity is not a ghost.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToGhost(Direction direction) {
        fail();
    }

    /**
     * Try to move the entity to a player.
     * Assert entity was moved and the game was ended iff the entity is a ghost.
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void moveToPlayer(Direction direction) {
        fail();
    }

    /**
     * Try to move the entity to outside the Board/Map.
     * Assert entity was (/NOT) moved and the game was (/NOT) ended.
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
