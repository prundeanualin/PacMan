package pacman.logic.entity;

import java.util.List;

import pacman.graphics.sprite.BlinkySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;


public class Blinky extends Ghost {

    private static final Sprite<Ghost> sprite = new BlinkySprite();

    public Blinky(Board board, Square square) {
        super(board, square, sprite);
        direction = Direction.LEFT;
    }

    @Override
    Square chooseTarget() {
        if (board.pacman == null) return null;
        else return board.pacman.getSquare();
    }
}
