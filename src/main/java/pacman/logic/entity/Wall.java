package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.graphics.sprite.WallSprite;
import pacman.logic.level.Board;

public class Wall extends Entity {

    private static final Sprite SPRITE = new WallSprite();

    public Wall(@NotNull Board board, int x, int y) {
        super(board, x + 0.5, y + 0.5, SPRITE);
        setSolid(true);
    }

}
