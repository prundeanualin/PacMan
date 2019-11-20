package pacman.gui.graphics;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an entity with a position, velocity and a sprite.
 *
 * @author Ruben
 */
public class Entity {

    private double x;
    private double y;
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
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    /**
     * Updates the entity's position.
     */
    public void update() {
        x += dx;
        y += dy;
    }

    /**
     * Gets the x position of this entity.
     * @return The x position
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y position of this entity.
     * @return The y position
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x position of this entity.
     * @param x The x position
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y position of this entity.
     * @param y The y position
     */
    public void setY(double y) {
        this.y = y;
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

    /**
     * Gets the sprite of this entity.
     * @return The sprite for rendering
     */
    public Sprite getSprite() {
        return sprite;
    }

}
