package pacman.gui.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import org.jetbrains.annotations.NotNull;

/**
 * Sprite for PacMan.
 *
 * @author Ruben
 */
public class PacmanSprite extends Sprite {

    @Override
    public void draw(@NotNull Entity entity, @NotNull GraphicsContext g, @NotNull Style style, double t) {
        g.setFill(style.getPacmanColour());
        // Triangle wave, angle goes between 0 and 40.
        double angle = 40 * Math.abs(2*t % 2 - 1);
        g.fillArc(-20, -20, 40, 40, angle, 360 - 2 * angle, ArcType.ROUND);
    }

}
