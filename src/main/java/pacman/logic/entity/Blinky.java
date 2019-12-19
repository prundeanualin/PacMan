package pacman.logic.entity;

import pacman.graphics.sprite.BlinkySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;


public class Blinky extends Ghost {

    private static final Sprite<Ghost> sprite = new BlinkySprite();
    private static Square HOME_CORNER;

    public Blinky(Board board, Square square) {
        super(board, square, sprite);
        direction = Direction.LEFT;
        HOME_CORNER = board.getSquare(board.getWidth(), 0);
    }

    /**
     * {@inheritDoc}
     * Blinky should always try to target PacMan.
     */
    @Override
    protected Square chaseTarget() {
        if (board.pacman == null) {
            return null;
        } else {
            return board.pacman.getSquare();
        }
    }

    /**
     * Getting the "home square' of each ghost, while in scattered mode.
     * @return Home square for blinky, which is top right.
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
