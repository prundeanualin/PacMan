package pacman.logic.entity;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PacmanSprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;

public class PacMan extends Entity {

    private static final Sprite SPRITE = new PacmanSprite();

    public PacMan(@NotNull Board board, double x, double y) {
        super(board, x, y, SPRITE);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        Set<Entity> collisions = checkCollision();
        collisions.stream().filter(e -> e instanceof Pellet).forEach(e -> e.setAlive(false));
        if (collisions.stream().anyMatch(Entity::isSolid)) {
            setDirection(Direction.DOWN);
        }
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
