package pacman.gui.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import org.jetbrains.annotations.NotNull;

/**
 * Sprite for a pellet.
 *
 * @author Ruben
 */
public class PelletSprite extends Sprite {

    @Override
    public void draw(@NotNull Entity entity, @NotNull GraphicsContext g, @NotNull Style style, double t) {
        g.setFill(style.getPelletColour());
        g.fillOval(-3, -3, 6, 6);
    }

}
