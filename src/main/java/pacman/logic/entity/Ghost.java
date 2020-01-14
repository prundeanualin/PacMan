package pacman.logic.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

/**
 * Represents a ghost.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class not a Bean.
public abstract class Ghost extends MovingEntity {

    public enum Mode {
        CHASE,
        SCATTER,
        FRIGHTENED;
    }

    protected Mode mode = Mode.CHASE;
    protected Square oldSquare;

    protected double homeX;
    protected double homeY;
    private Square homeCorner;

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
        oldSquare = square;
        homeCorner = square;
        this.homeX = getX();
        this.homeY = getY();
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        // Collided with PacMan
        Set<Entity> collisions = checkCollision();
        if (collisions.contains(board.pacman) && !board.pacman.isImmune()
                && mode != Mode.FRIGHTENED) {
            board.pacman.setAlive(false);
        }

        if (square != oldSquare) { // Update choice when a new square is reached.
            List<Square> options = getOptions();
            Square target = chooseTarget(options);
            if (options.size() > 0 && target != null) {
                Square next = closestNeighbour(target, options);
                nextDirection = square.directionOf(next);
            }
            oldSquare = square; // must be called after getOptions, as this information is used.
        }
    }

    /**
     * Returns a list of all options a ghost has as next squares.
     *
     * @return list of target square options.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // For each loop false warning.
    protected List<Square> getOptions() {
        List<Square> neighbours = this.getSquare().getNeighbours();
        List<Square> options = new ArrayList<>(4);

        for (Square square : neighbours) {
            if (!square.hasSolid() && !square.equals(oldSquare)) {
                options.add(square);
            }
        }
        return options;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    // Foreach loop incorrectly marked as UR anomaly.
    protected Square closestNeighbour(Square target, List<Square> options) {
        if (options.size() == 0) {
            throw new IllegalArgumentException("Cannot choose target from empty list of options.");
        }

        float min = Float.MAX_VALUE;
        Square next = null;

        for (Square s : options) {
            int xdir = Math.abs(target.getXs() - s.getXs());
            int ydir = Math.abs(target.getYs() - s.getYs());
            float dist = xdir + ydir;
            if (dist < min) {
                min = dist;
                next = s;
            }
        }
        return next;
    }

    /**
     * Choose what direction the ghost should go in.
     *
     * @param nextOptions the neighbouring squares the ghost can go to.
     * @return the square the ghost wants to go towards.
     * @see this#chaseTarget()
     * @see this#scatterTarget(List)
     * @see this#frightenedTarget(List) 
     */
    protected Square chooseTarget(List<Square> nextOptions) {
        switch (mode) {
            case CHASE:
                return chaseTarget();
            case SCATTER:
                return scatterTarget(nextOptions);
            case FRIGHTENED:
                return frightenedTarget(nextOptions);
            default:
                throw new IllegalStateException("No target behavior implemented for: "
                        + mode.toString());
        }
    }

    /**
     * Chooses the default chase mode target of the ghost.
     *
     * @return the target
     * @see this#chooseTarget(List)
     */
    protected abstract Square chaseTarget();

    /**
     * Getting the "home square' of each ghost, while in scattered mode.
     * @return That specific home_square, the starting point of each ghost.
     */
    private final Square scatterTarget(List<Square> options) {
        if (square == homeCorner) {
            Random rand = new Random();
            return options.get(rand.nextInt(options.size()));
        } else {
            return homeCorner;
        }
    }

    /**
     * Chooses the frightened mode target of the ghost.
     *
     * @param options the neighbouring options the ghost can access.
     * @return the target (should be randomly picked from intersection options)
     * @see this#chooseTarget(List) 
     */
    private final Square frightenedTarget(List<Square> options) {
        int a = ThreadLocalRandom.current().nextInt(0, options.size());
        return options.get(a);
    }

    public double getHomeX() {
        return homeX;
    }

    public double getHomeY() {
        return homeY;
    }

}
