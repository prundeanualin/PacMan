package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

/**
 * Entity template for entities that move around the board. This includes Ghosts & PacMan.
 */
public abstract class MovingEntity extends Entity {

    /**
     * Creates an entity at the specified position with the specified sprite.
     *
     * @param board  The board the entity belongs to
     * @param square The square the entity belongs to
     * @param sprite The sprite for rendering
     */
    public MovingEntity(@NotNull Board board, Square square, @NotNull Sprite<? extends Entity> sprite) {
        super(board, square, sprite);
    }

    /**
     * Updates the entity's position during the game cycle.
     */
    public void update(double dtSmall) {
        Square square = getSquare(); // NOPMD variable is used
        // If no collision with solid entities and entity is moving
        double dt = 2 * dtSmall; //NOPMD needed to change the speed of the entities' movement
        if (direction != null) {
            posX += dt * direction.getX();
            posY += dt * direction.getY();

            if (checkCollision().stream().anyMatch(Entity::isSolid)) {
                posX -= dt * direction.getX();
                posY -= dt * direction.getY();
            } else {
                Square newSquare = getSquare();
                // Check if entity moved squares
                if (!square.equals(newSquare)) {
                    moveToSquare(newSquare);
                }
                // Wraparound
                posX = board.getPosX(posX);
                posY = board.getPosY(posY);
            }
        }

        if (nextDirection != null && nextDirection != getDirection()) {
            // Get distance to center of square
            double dx = Math.abs(getX() - Math.floor(getX()) - 0.5);
            double dy = Math.abs(getY() - Math.floor(getY()) - 0.5);
            /*
             * Entities changes direction if the set direction is opposite their current direction
             * or if they are at the center of the square.
             */
            if (getDirection() == nextDirection.getInverse() || (dx < 0.02 && dy < 0.02)) {
                setDirection(nextDirection);
            }
        }
    }
}
