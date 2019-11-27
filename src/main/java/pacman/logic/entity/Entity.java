package pacman.logic.entity;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

/**
 * Represents an entity with a position, velocity and a sprite.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public abstract class Entity {

    private Board board;
    private double posX;
    private double posY;
    private Sprite<? extends Entity> sprite;

    private Direction direction = null;
    private boolean alive = true;

    private boolean solid = false;

    /**
     * Creates an entity at the specified position with the specified sprite.
     *
     * @param board The board the entity belongs to
     * @param x The x position of the entity
     * @param y The y position of the entity
     * @param sprite The sprite for rendering
     */
    public Entity(@NotNull Board board, double x, double y,
                  @NotNull Sprite<? extends Entity> sprite) {
        this.board = board;
        this.posX = x;
        this.posY = y;
        this.sprite = sprite;
    }

    /**
     * Checks whether this entity collides with another entity.
     * @param other The other entity to check collision with
     * @return Whether the two entities collide
     */
    public boolean collide(Entity other) {
        return false;
    }

    /**
     * Updates the entity's position.
     */
    public void update(double dt) {
        Square square = board.getSquare((int)posX, (int)posY); // NOPMD variable is used
        if (checkCollision().stream().noneMatch(Entity::isSolid) && direction != null) {
            posX += dt * direction.getDeltaX();
            posY += dt * direction.getDeltaY();
            Square newSquare = board.getSquare((int)posX, (int)posY);
            if (square.equals(newSquare)) {
                square.moveEntityTo(this, newSquare);
            }
            if (posX < 0) {
                posX += board.getWidth();
            } else if (posX >= board.getWidth()) {
                posX -= board.getWidth();
            }
            if (posY < 0) {
                posY += board.getHeight();
            } else if (posY >= board.getHeight()) {
                posY -= board.getHeight();
            }
        }
    }

    /**
     * Checks for collisions with the entities around this entity.
     * @return The set of entities this entity collides with
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public Set<Entity> checkCollision() {
        Set<Entity> collisions = new HashSet<>();
        for (Entity entity : board.getSquare((int)posX, (int)posY).getEntities()) {
            if (entity != this && collide(entity)) {
                collisions.add(entity);
            }
        }
        if (direction != null) {
            for (Entity entity : board.getSquare((int)posX + direction.getDeltaX(),
                    (int)posY + direction.getDeltaY()).getEntities()) {
                if (entity != this && collide(entity)) {
                    collisions.add(entity);
                }
            }
        }
        return collisions;
    }

    /**
     * Gets the x position of this entity.
     * @return The x position
     */
    public double getX() {
        return posX;
    }

    /**
     * Gets the y position of this entity.
     * @return The y position
     */
    public double getY() {
        return posY;
    }

    /**
     * Sets the x position of this entity.
     * @param x The x position
     */
    public void setX(double x) {
        this.posX = x;
    }

    /**
     * Sets the y position of this entity.
     * @param y The y position
     */
    public void setY(double y) {
        this.posY = y;
    }

    /**
     * Gets the direction this entity is moving in.
     * @return The direction, null if entity has no direction
     */
    public @Nullable Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of this entity.
     * @param direction The new direction or null if no direction
     */
    public void setDirection(@Nullable Direction direction) {
        this.direction = direction;
    }

    /**
     * Gets the sprite of this entity.
     * @return The sprite for rendering
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Gets whether this entity is solid an cannot be passed though.
     * @return Whether this entity is solid
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * Sets the solidity of this entity.
     * @param solid Whether the entity is solid or not
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    /**
     * Gets the board this entity is on.
     * @return The board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the absolute distance to the specified position.
     * @param x The x position
     * @return The distance to the position
     */
    protected double distanceX(double x) {
        double dx = Math.abs(posX - x);
        if (2 * dx > board.getWidth()) {
            return board.getWidth() - dx;
        }
        return dx;
    }

    /**
     * Gets the absolute distance to the specified position.
     * @param y The y position
     * @return The distance to the position
     */
    protected double distanceY(double y) {
        double dy = Math.abs(posY - y);
        if (2 * dy > board.getHeight()) {
            return board.getHeight() - dy;
        }
        return dy;
    }

    /**
     * Gets whether the entity is alive.
     * @return The alive status of this entity
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the alive status of this entity.
     * @param alive The new alive status
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets the square this entity is on.
     * @return The current square
     */
    public @NotNull Square getSquare() {
        return board.getSquare((int)posX, (int)posY);
    }

}
