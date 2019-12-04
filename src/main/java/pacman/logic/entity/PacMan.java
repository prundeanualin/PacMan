package pacman.logic.entity;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PacmanSprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;

/**
 * Represents the PacMan entity on the board.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class PacMan extends Entity {

    private static final Sprite<PacMan> SPRITE = new PacmanSprite();

    private Direction nextDirection = null;

    /**
     * Creates a new PacMan.
     * @param board The board PacMan is on
     * @param x PacMan's x coordinate
     * @param y PacMan's y coordinate
     */
    public PacMan(@NotNull Board board, double x, double y) {
        super(board, x, y, SPRITE);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (nextDirection != null && nextDirection != getDirection()) {
            // Get distance to center of square
            double dx = Math.abs(getX() - Math.floor(getX()) - 0.5);
            double dy = Math.abs(getY() - Math.floor(getY()) - 0.5);
            /*
             * PacMan changes direction if the set direction is opposite his current direction
             * or if he is at the center of the square.
             */
            if (getDirection() == nextDirection.getInverse() || (dx < 0.05 && dy < 0.05)) {
                if (getDirection() != nextDirection.getInverse()) {
                    setX(Math.floor(getX()) + 0.5);
                    setY(Math.floor(getY()) + 0.5);
                }
                setDirection(nextDirection);
            }
        }
        Set<Entity> collisions = checkCollision();
        // Set every collided pellet to dead
        collisions.stream().filter(e -> e instanceof Pellet).forEach(e -> e.setAlive(false));
    }

    /**
     * Sets the direction PacMan will go in at the next intersection.
     * @param nextDirection The next direction
     */
    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    @Override
    public boolean collide(Entity other) {
        double dx = distanceX(other.getX()); // NOPMD variable is used
        double dy = distanceY(other.getY()); // NOPMD variable is used
        if (other instanceof Pellet) {
            return dx * dx + dy * dy < 0.25;
        }
        if (other instanceof Wall) {
            return dx < 1.0 && dy < 1.0;
        }
        return false;
    }
}
