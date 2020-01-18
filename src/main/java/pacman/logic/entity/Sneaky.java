package pacman.logic.entity;

import pacman.graphics.sprite.SneakySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

import java.util.List;

public class Sneaky extends Ghost {

    private static final Sprite<Ghost> sprite = new SneakySprite();
    private static Square HOME_CORNER;

    /**
     * Creating Sneaky.
     * @param board the board
     * @param square Sneaky's square
     */
    public Sneaky(Board board, Square square) {
        super(board, square, sprite);
        direction = Direction.LEFT;
        HOME_CORNER = board.getSquare(board.getWidth(), board.getHeight());//NOPMD
        // needed to initialize it here with board as parameter
    }

    /**
     * {@inheritDoc}
     * Sneaky is always around its home area.
     */
    @Override
    protected Square chaseTarget(List<Square> options) {
        return scatterTarget();
    }

    /**
     * {@inheritDoc}
     * @return Home square for Sneaky, which is bottom right.
     */

    @Override
    protected Square scatterTarget() {
        if (oldSquare == HOME_CORNER) {
            return HOME_CORNER.getNeighbour(Direction.DOWN);
        } else {
            return HOME_CORNER;
        }
    }
}
