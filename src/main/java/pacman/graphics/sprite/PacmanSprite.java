package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.PacMan;

/**
 * Sprite for PacMan.
 *
 * @author Ruben
 */
public class PacmanSprite extends Sprite<PacMan> {

    @Override
    public void draw(@NotNull PacMan entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        if (entity.getDirection() != null) {
            g.rotate(-Math.toDegrees(entity.getDirection().getRotation()));
        }
        g.setFill(style.getPacmanColour());
        // Triangle wave, angle goes between 0 and 40.
        double angle = 40 * Math.abs(2 * t % 2 - 1);
        g.fillArc(-0.4, -0.4, 0.8, 0.8, angle, 360 - 2 * angle, ArcType.ROUND);
    }

}
