package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.BoardCanvas;
import pacman.graphics.Style;
import pacman.logic.entity.Entity;
import pacman.logic.level.Board;

public class WallSprite extends Sprite {

    @Override
    public void draw(@NotNull Entity entity, @NotNull GraphicsContext g, @NotNull Style style, double t) {
        g.setFill(style.getWallColour());
        g.fillRect(0, 0, BoardCanvas.scaleX, BoardCanvas.scaleY);
    }
}
