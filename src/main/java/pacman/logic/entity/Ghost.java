package pacman.logic.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.GameController;
import pacman.logic.level.Board;
import pacman.logic.level.Square;


/**
 * Represents a ghost.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class not a Bean.
public abstract class Ghost extends MovingEntity {

    static PacMan pacMan;
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
            List<Square> options = getOptions();
            if (options.size() > 0) {
                Square target = chooseTarget();
                Square next= closestNeighbour(target);
                nextDirection = square.directionOf(next);
            }
            oldSquare = square; // must be called after getOptions, as this information is used.
        }
    }

    /**
     * Returns a list of all options a ghost has as target squares.
     *
     * @return list of target square options.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // For each loop false warning.
    private List<Square> getOptions() {
        List<Square> neighbours = this.getSquare().getNeighbours();
        List<Square> options = new ArrayList<>(4);

        for (Square square : neighbours) {
            if (!square.hasSolid() && !square.equals(oldSquare)) {
                options.add(square);
            }
        }
        return options;
    }

    /**
     * Choose what direction the ghost should go in.
     *
     * @return the square the ghost wants to go towards.
     */
    abstract Square chooseTarget();

    private Square closestNeighbour(Square target) {
        List<Square> options= getOptions();
        if (options.size() == 0) {
            throw new IllegalArgumentException("Cannot choose target from empty list of options.");
        }

        float min = Float.MAX_VALUE;
        Square next = null;

        for (Square s : options) {
            int x_dir = Math.abs(target.getX() - s.getX());
            int y_dir = Math.abs(target.getY() - s.getY());
            float dist = x_dir + y_dir;
            if (dist < min) {
                min = dist;
                next = s;
            }
        }
        return next;
    }
}
