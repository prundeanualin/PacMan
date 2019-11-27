package pacman.logic.entity;

import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;

public class Blinky extends Ghost {

    private static final Sprite<Ghost> sprite = null;

    public Blinky(Board board, double x, double y) {
        super(board, x, y, sprite);
    }
}
