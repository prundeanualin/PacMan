package pacman.logic.entity;

import pacman.graphics.sprite.PinkySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

@SuppressWarnings("PMD")
public class Pinky extends Ghost {

    private static final Sprite<Ghost> sprite = new PinkySprite();
    private static Square HOME_CORNER;

    /**
     * Creating Pinky.
     * @param board the board
     * @param square Pinky's square
     */
    public Pinky(Board board, Square square) {
        super(board, square, sprite);
        direction = Direction.LEFT;
        HOME_CORNER = board.getSquare(0, 0);//NOPMD
        // needed to initialize it here with board as parameter
    }

    /**
     * {@inheritDoc}
     * Pinky should always try to target the square 4 ahead of PacMan.
     */
    @Override
    protected Square chaseTarget() {
        PacMan pac = board.pacman;
        if (pac == null) {
            return null;
        }
        Square pacSquare = pac.getSquare();
        Direction pacDir = board.pacman.getDirection();
        int x = pacSquare.getXs() + pacDir.getDx() * 4;
        int y = pacSquare.getYs() + pacDir.getDy() * 4;

        Math.max(0, Math.min(x, board.getWidth() - 1));
        Math.max(0, Math.min(y, board.getHeight() - 1));
        // It's not a problem if this is a wall, Pinky would circle around it.
        return getBoard().getSquare(x, y);
    }

    /**
     * Getting the "home square' of each ghost, while in scattered mode.
     * @return Home square for Pinky, which is top left.
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