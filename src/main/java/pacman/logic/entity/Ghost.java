package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;

public abstract class Ghost extends Entity {

    public Ghost(@NotNull Board board, double x, double y, @NotNull Sprite sprite) {
        super(board, x, y, sprite);
    }

}
