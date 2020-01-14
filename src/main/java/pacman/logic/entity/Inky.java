package pacman.logic.entity;

import pacman.graphics.sprite.InkySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

public class Inky extends Ghost {

    private static final Sprite<Ghost> sprite = new InkySprite();
    private static Square HOME_CORNER;

    /**
     * Creating Inky.
     * @param board the board
     * @param square Inky's square
     */
    public Inky(Board board, Square square) {
        super(board, square, sprite);
        direction = Direction.LEFT;
        HOME_CORNER = board.getSquare(0, board.getHeight());//NOPMD
        // needed to initialize it here with board as parameter
    }

    /**
     * Inky is drunk so always in frightened mode.
     * @return next square Inky will move to.
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
