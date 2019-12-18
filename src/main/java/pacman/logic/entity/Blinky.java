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

    /**
     * {@inheritDoc}
     * Blinky should always try to target PacMan.
     */
    @Override
    protected Square chaseTarget() {
        if (board.pacman == null) {
            return null;
        }
        return board.pacman.getSquare();
    }

    @Override
    protected Square scatterTarget() {
        //TODO implement blinky's scatter:
        return null;
    }
}
