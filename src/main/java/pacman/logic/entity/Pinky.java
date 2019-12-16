package pacman.logic.entity;

import pacman.graphics.sprite.PinkySprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

public class Pinky extends Ghost {

    private static final Sprite<Ghost> sprite = new PinkySprite();

    public Pinky(Board board, Square square) {
        super(board, square, sprite);
        direction = Direction.LEFT;
    }

    @Override
    Square chooseTarget() {
        PacMan pac = board.pacman;
        if (pac == null) return null;
        Square pacSquare = pac.getSquare();
        Direction pacDir = board.pacman.getDirection();
        int x = pacSquare.getX() + pacDir.getX() * 4;
        int y = pacSquare.getY() + pacDir.getY() * 4;

        Math.max(0, Math.min(x, board.getWidth() - 1));
        Math.max(0, Math.min(y, board.getHeight() - 1));
        // It's not a problem if this is a wall, Pinky would circle around it.
        return getBoard().getSquare(x, y);
    }
}