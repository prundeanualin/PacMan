package pacman.logic.entity;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PacmanSprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class PacMan extends Entity {

    private static final Sprite<PacMan> SPRITE = new PacmanSprite();

    private Direction nextDirection = null;

    public PacMan(@NotNull Board board, double x, double y) {
        super(board, x, y, SPRITE);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (nextDirection != null && nextDirection != getDirection()) {
            double dx = Math.abs(getX() - (int)getX() - 0.5);
            double dy = Math.abs(getY() - (int)getY() - 0.5);
            if (getDirection() == nextDirection.getInverse() || (dx < 0.02 && dy < 0.02)) {
                setDirection(nextDirection);
            }
        }
        Set<Entity> collisions = checkCollision();
        collisions.stream().filter(e -> e instanceof Pellet).forEach(e -> e.setAlive(false));
    }

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
