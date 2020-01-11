package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Ghost;

public abstract class GhostSprite extends Sprite<Ghost> {

    protected void drawHome(@NotNull Ghost ghost, @NotNull GraphicsContext g, Color color,
                            Color background) {
        double x = ghost.getHomeX() - ghost.getX();
        double y = ghost.getHomeY() - ghost.getY();
        g.setFill(color.interpolate(background, 0.8));
        g.fillRect(x - 0.25, y - 0.25, 0.5, 0.5);
    }

}
