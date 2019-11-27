package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.graphics.sprite.WallSprite;
import pacman.logic.level.Board;

/**
 * Represents a wall.
 */
public class Wall extends Entity {

    private static final Sprite<Wall> SPRITE = new WallSprite();

    /**
     * Creates a wall.
     * @param board The board the wall is on
     * @param x The x coordinate of the wall
     * @param y The y coordinate of the wall
     */
    public Wall(@NotNull Board board, int x, int y) {
        super(board, x + 0.5, y + 0.5, SPRITE);
        setSolid(true);
    }

}
