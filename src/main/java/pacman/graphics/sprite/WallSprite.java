package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Entity;

public class WallSprite extends Sprite {

    @Override
    public void draw(@NotNull Entity entity, @NotNull GraphicsContext g, @NotNull Style style, double t) {
        g.setStroke(style.getWallColour());
        g.strokeRect(-0.5, -0.5, 1, 1);
    }

}
