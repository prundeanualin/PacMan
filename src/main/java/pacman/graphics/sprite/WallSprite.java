package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Wall;

/**
 * Sprite for the walls.
 */
public class WallSprite extends Sprite<Wall> {

    @Override
    public void draw(@NotNull Wall entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        g.setStroke(style.getWallColour());
        g.fillRect(-0.5, -0.5, 1, 1);
    }

}
