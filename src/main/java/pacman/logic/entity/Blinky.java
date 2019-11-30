package pacman.logic.entity;

import java.util.List;
import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;
import pacman.logic.level.Square;


public class Blinky extends Ghost {

    private static final Sprite<Ghost> sprite = null;

    public Blinky(Board board, Square square) {
        super(board, square, sprite);
    }

    @Override
    Square chooseTarget(List<Square> options) {
        //TODO: calculate distances to pacman (or his square?)
        // from the options, and choose the direction to the option that gives the smallest.
        Square pac = pacMan.getSquare();
        int pacX = pac.getX();
        int pacY = pac.getY();
        int min = Integer.MAX_VALUE;
        Square target = null;
        for (Square s: options) {
            int x_dir = pacX - s.getX();
            int y_dir = pacY - s.getY();
            if ((x_dir + y_dir) < min) {
                min = x_dir + y_dir;
                target = s;
            }
        }

        if (target == null) {
            return options.get(0);
        }

        return target;
    }
}
