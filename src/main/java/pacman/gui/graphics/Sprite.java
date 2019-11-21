package pacman.gui.graphics;

import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a drawable sprite.
 *
 * @author Ruben
 */
public abstract class Sprite {

    /**
     * Draws the sprite.
     *
     * @param entity The entity the sprite belongs to
     * @param g The graphics context to draw with
     * @param style The style to draw in
     * @param t The time in seconds
     */
    public abstract void draw(@NotNull Entity entity, @NotNull GraphicsContext g, @NotNull Style style, double t);

}