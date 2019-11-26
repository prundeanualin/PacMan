package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.BoardCanvas;
import pacman.graphics.Style;
import pacman.logic.entity.Entity;
import pacman.logic.level.Board;

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
        g.fillOval(BoardCanvas.scaleX / 2 - BoardCanvas.scaleX / 6, BoardCanvas.scaleY / 2
                - BoardCanvas.scaleY / 6, BoardCanvas.scaleX / 3, BoardCanvas.scaleY / 3);
    }

}
