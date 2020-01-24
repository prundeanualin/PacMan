package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.Direction;
import pacman.logic.entity.Wall;
import pacman.logic.level.Square;

/**
 * Sprite for the walls.
 */
public class WallSprite extends Sprite<Wall> {

    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void draw(@NotNull Wall entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        g.setStroke(style.getWallColor());
        for (Direction dir : Direction.values()) {
            Square neighbour = entity.getSquare().getNeighbour(dir);
            double dist = Math.abs(entity.getX() - neighbour.getXs())
                    + Math.abs(entity.getY() - neighbour.getYs());
            if (dist > 2 || !neighbour.hasSolid()) {
                double x1 = dir.getDx() == 0 ? -0.5 : dir.getDx() * 0.5;
                double x2 = dir.getDx() == 0 ? 0.5 : dir.getDx() * 0.5;
                double y1 = dir.getDy() == 0 ? -0.5 : dir.getDy() * 0.5;
                double y2 = dir.getDy() == 0 ? 0.5 : dir.getDy() * 0.5;
                g.strokeLine(x1, y1, x2, y2);
            }
        }
    }
}
