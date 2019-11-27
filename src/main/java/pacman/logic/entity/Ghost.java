package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;

/**
 * Represents a ghost.
 */
public abstract class Ghost extends Entity {

    /**
     * Creates a ghost.
     * @param board The board the ghost is on
     * @param x The x coordinate of the ghost
     * @param y The y coordinate of the ghost
     * @param sprite The sprite of the ghost
     */
    public Ghost(@NotNull Board board, double x, double y,
                 @NotNull Sprite<? extends Ghost> sprite) {
        super(board, x, y, sprite);
    }

}
