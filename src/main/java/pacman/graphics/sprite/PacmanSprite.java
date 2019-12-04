package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.PacMan;

/**
 * Sprite for PacMan.
 */
public class PacmanSprite extends Sprite<PacMan> {

    private static final double MAX_ANGLE = 40.0;
    private static final double BLINK_TIME = 0.8;

    @Override
    public void draw(@NotNull PacMan entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        if (entity.getDirection() != null) {
            g.rotate(-Math.toDegrees(entity.getDirection().getRotation()));
        }
        g.setFill(style.getPacmanColour());
        // Triangle wave, angle goes between 0 and 40.
        double angle = MAX_ANGLE * Math.abs(2 * t % 2 - 1); // NOPMD variable necessary
        if (!entity.isImmune()) {
            g.fillArc(-0.4, -0.4, 0.8, 0.8, angle, 360 - 2 * angle, ArcType.ROUND);
        } else if (t - Math.floor(t) < BLINK_TIME) {
            g.fillArc(-0.4, -0.4, 0.8, 0.8, MAX_ANGLE / 2, 360 - MAX_ANGLE, ArcType.ROUND);
        }
    }

}
