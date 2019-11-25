package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;

public class PacMan extends Entity {

    public PacMan(@NotNull Board board, double x, double y, @NotNull Sprite sprite) {
        super(board, x, y, sprite);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (checkCollision().stream().anyMatch(Entity::isSolid)) {
            setDirection(Direction.DOWN);
        }
    }

    @Override
    public boolean collide(Entity other) {
        double dx = distanceX(other.getX());
        double dy = distanceY(other.getY());
        if (other instanceof Pellet) {
            return dx * dx + dy * dy < 0.25;
        }
        if (other instanceof Wall) {
            return dx < 1.0 && dy < 1.0;
        }
        return false;
    }
}
