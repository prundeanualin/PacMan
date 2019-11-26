package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;

/**
 * Represents an entity with a position, velocity and a sprite.
 *
 * @author Ruben
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public abstract class Entity {

    private double posX;
    private double posY;
    private Sprite sprite;

    private double dx = 0;
    private double dy = 0;

    /**
     * Creates an entity at the specified position with the specified sprite.
     *
     * @param x The x position of the entity
     * @param y The y position of the entity
     * @param sprite The sprite for rendering
     */
    public Entity(double x, double y, @NotNull Sprite sprite) {
        this.posX = x;
        this.posY = y;
        this.sprite = sprite;
    }

    /**
     * Updates the entity's position.
     */
    public void update(double dt) {
        posX += dt * dx;
        posY += dt * dy;
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
     * Gets the x velocity of this entity.
     * @return The velocity in the x direction
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the y velocity of this entity.
     * @return The velocity in the y direction
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the x velocity of this entity.
     * @param dx The velocity in the x direction
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the y velocity of this entity.
     * @param dy The velocity in the y direction
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setDirection(Direction dir) {
        this.dx = dir.getDeltaX();
        this.dy = dir.getDeltaY();
    }

    /**
     * Gets the sprite of this entity.
     * @return The sprite for rendering
     */
    public Sprite getSprite() {
        return sprite;
    }

}
