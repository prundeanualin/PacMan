package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.graphics.sprite.WallSprite;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

/**
 * Represents a wall.
 */
public class Wall extends Entity {

    private static final Sprite<Wall> SPRITE = new WallSprite();

    /**
     * Creates a wall.
     *
     * @param board  The board the wall is on
     * @param square The square the wall is on
     */
    public Wall(@NotNull Board board, Square square) {
        super(board, square, SPRITE);
        setSolid(true);
    }

    @Override
    public void update(double dtSmall) {
    }
}
