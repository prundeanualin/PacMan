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
    Square chooseTarget(List<Square> options) {
        if (options.size() == 0) {
            throw new IllegalArgumentException("Cannot choose target from empty list of options.");
        }
        Square pac = pacMan.getSquare();
        float min = Float.MAX_VALUE;
        Square target = null;

        for (Square s : options) {
            int x_dir = Math.abs(pac.getX() - s.getX());
//            if (x_dir > getBoard().getWidth() / 2) {
//                x_dir = getBoard().getWidth() - x_dir;
//            }
            int y_dir = Math.abs(pac.getY() - s.getY());
//            if (y_dir > getBoard().getHeight() / 2) {
//                y_dir = getBoard().getHeight() - y_dir;
//            }
            float dist = x_dir + y_dir;
            if (dist < min) {
                min = dist;
                target = s;
            }
        }
        return target;
    }
}
