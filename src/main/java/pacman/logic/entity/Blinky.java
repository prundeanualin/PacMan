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
        direction= Direction.LEFT;
    }

    @Override
    Square chooseTarget(List<Square> options) {
        Square pac = pacMan.getSquare();
        int pacX = pac.getX();
        int pacY = pac.getY();
        int min = Integer.MAX_VALUE;
        Square target = null;
//        System.out.println(options.size());
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
