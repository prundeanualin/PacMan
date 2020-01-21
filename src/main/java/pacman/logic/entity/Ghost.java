package pacman.logic.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
        EATEN,
        SCARED;

        /**
         * Switching between CHASE and SCATTER modes.
         * @return the other one of those two.
         */
        public Mode scatter() {
            if (this == CHASE) {
                return SCATTER;
            } else {
                return CHASE;
            }
        }
    }

    protected Mode mode = Mode.CHASE;
    protected Square oldSquare;

    protected double homeX;
    protected double homeY;
    private Square homeCorner;
    private double time;    //keeps track of time passed since last 'scatter' tick
    private static final double scatterTimer = 18.0;
    private static final double scatterDuration = 7.0;

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
        time = 0.0;
    }

    @Override
    public void update(double dt) {

        // if frightened, our ghost moves 2 times slower
        if (mode == Mode.SCARED) {
            super.update(dt / 2);
        } else if (mode == Mode.EATEN) {
            super.update(2 * dt);
        } else {
            super.update(dt);
        }

        time = time + dt;
        // Alternating between chase mode and scatter mode according to the timers.
        if (mode == Mode.CHASE && time > scatterTimer || mode == Mode.SCATTER
                && time > scatterDuration) {
            mode = mode.scatter();
            time = 0.0;
            nextDirection = direction.getInverse();
            oldSquare = square;
        }
        // Collided with PacMan
        Set<Entity> collisions = checkCollision();
        if (collisions.contains(board.pacman) && !board.pacman.isImmune()
                && mode != Mode.SCARED && mode != Mode.EATEN) {
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
            if (neighbours.size() == 1 || (mode == Mode.EATEN && !square.hasSolid())
                || (!square.hasSolid() && !square.equals(oldSquare))) {
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

        double min = Double.MAX_VALUE;
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
            case SCARED:
                return frightenedTarget(nextOptions);
            case EATEN:
                return spawnTarget();
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
        Random random = new Random();
        int a = random.nextInt(options.size());
        return options.get(a);
    }

    /**
    * Return the home square of each ghost, in order to
    * respawn at that location.
    * @return that home square
    */
    private Square spawnTarget() {
        if (square == homeCorner) {
            mode = Mode.CHASE;
            time = 0.0;
            return chaseTarget();
        } else {
            return homeCorner;
        }
    }

    public boolean isScared() {
        return mode == Mode.SCARED;
    }

    public void beScared() {
        mode = Mode.SCARED;
    }

    public void unScare() {
        mode = Mode.CHASE;
        time = 0.0;
    }

    public void justEaten() {
        mode = Mode.EATEN;
    }

    public boolean isEaten() {
        return mode == Mode.EATEN;
    }

    public double getHomeX() {
        return homeX;
    }

    public double getHomeY() {
        return homeY;
    }

}
