package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.BoardCanvas;
import pacman.graphics.Style;
import pacman.logic.entity.Entity;

/**
 * Sprite for PacMan.
 *
 * @author Ruben
 */
public class PacmanSprite extends Sprite {

    @Override
    public void draw(@NotNull Entity entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        g.setFill(style.getPacmanColour());
        // Triangle wave, angle goes between 0 and 40.
        double angle = 40 * Math.abs(2 * t % 2 - 1);
        g.fillArc(0, 0, BoardCanvas.scaleX, BoardCanvas.scaleY, angle, 360 - 2 * angle, ArcType.ROUND);
    }

}
