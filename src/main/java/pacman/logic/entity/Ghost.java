package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.GameController;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

import java.util.List;
import java.util.Set;

/**
 * Represents a ghost.
 */
public abstract class Ghost extends Entity {

    static PacMan pacMan;
    private Square target;
    private Square oldSquare;

    /**
     * Creates a ghost.
     *
     * @param board  The board the ghost is on
     * @param square the square the ghost is on
     * @param sprite The sprite of the ghost
     */
    public Ghost(Board board, Square square, Sprite<? extends Ghost> sprite) {
        super(board, square, sprite);
        direction = Direction.RIGHT;
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        // Collided with PacMan
        Set<Entity> collisions = checkCollision();
        if (collisions.contains(pacMan)) {
            pacMan.setAlive(false);
            GameController.getInstance().getGame().setRunning(false);
        }

        if (square != oldSquare) { // Update choice when a new square is reached.
            Direction choice = chooseDirection();
            assert (choice != direction.getInverse());
            nextDirection = choice;
            oldSquare = square;
        }
    }

    /**
     * Returns a list of all options a ghost has as target squares.
     *
     * @return list of target square options.
     */
    protected List<Square> getOptions() {
        List<Square> options = square.getNeighbours();
        for (Square s : options) {
            if (s.hasSolid() || s == oldSquare) {
                options.remove(s);
            }
        }
        return options;
    }

    /**
     * Choose what direction the ghost should go in.
     *
     * @return the direction the ghost wants to go in.
     */
    abstract Direction chooseDirection();
}
