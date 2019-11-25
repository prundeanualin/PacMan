package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Entity;

/**
 * Sprite for a pellet.
 *
 * @author Ruben
 */
public class PelletSprite extends Sprite {

    @Override
    public void draw(@NotNull Entity entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        g.setFill(style.getPelletColour());
        g.fillOval(-0.1, -0.1, 0.2, 0.2);
    }

}
