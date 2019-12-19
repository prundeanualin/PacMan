package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.PacMan;

/**
 * Sprite for PacMan.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Not a bean.
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

    /**
     * Draws a pacman sad/happy face.
     * @param entity pacman
     * @param gc graphics of the board canvas
     * @param style style
     * @param alive happy/sad
     */
    public void animation(@NotNull PacMan entity, @NotNull GraphicsContext gc, @NotNull Style style, boolean alive) {
        gc.setFill(style.getPacmanColour());
        gc.fillOval(-0.4, -0.4, 0.8, 0.8);
        gc.setFill(Color.BLACK);
        gc.fillOval(-0.2, 0.2, 0.1, 0.1);
        gc.fillOval(0.2, 0.2, 0.1, 0.1);
        if (alive) {
            gc.strokeArc(-0.2, -0.2, 0.4, 0.3, 180, 360, ArcType.CHORD);
        } else {
            gc.strokeArc(-0.2, -0.2, 0.4, 0.3, -180, 0, ArcType.CHORD);
        }
    }
}
