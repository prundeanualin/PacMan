package pacman.logic.entity;

import pacman.graphics.sprite.DrunkySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

public class Drunky extends Ghost {

    private static final Sprite<Ghost> sprite = new DrunkySprite();
    private static Square HOME_CORNER;

    /**
     * Creating Drunky.
     * @param board the board
     * @param square Drunky's square
     */
    public Drunky(Board board, Square square) {
        super(board, square, sprite);
        direction = Direction.LEFT;
        HOME_CORNER = board.getSquare(0, board.getHeight());//NOPMD
        // needed to initialize it here with board as parameter
    }

    /**
     * Drunky is drunk so always in frightened mode.
     * @return next square Drunky will move to.
     */
    @Override
    protected Square chaseTarget() {

        return frightenedTarget(getOptions());
    }

    /**
     * Getting the "home square' of each ghost, while in scattered mode.
     * @return Home square for inky, which is bottom left.
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
