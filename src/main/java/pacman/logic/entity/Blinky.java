package pacman.logic.entity;

import pacman.graphics.sprite.BlinkySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

@SuppressWarnings("PMD")
public class Blinky extends Ghost {

    private static final Sprite<Ghost> sprite = new BlinkySprite();

    /**
     * Creating Blinky.
     * @param board the board
     * @param square Blinky's square
     */
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
}
