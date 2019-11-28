package pacman.logic.entity;

import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;
import java.util.List;
import pacman.logic.level.Square;


public class Blinky extends Ghost {

    private static final Sprite<Ghost> sprite = null;

    public Blinky(Board board, Square square) {
        super(board, square, sprite);
    }

    @Override
    Square chooseTarget(List<Square> options) {
        //TODO: calculate distances to pacman (or his square?)
        // from the options, and choice the direction to the option that gives the smallest.
//      Direction nextDir;
        for (Square s: options) {
            for(Entity n: s.getEntities()) {
                if(n.equals(pacMan)) {
//                  nextDir= square.directionOf(s);
                    return s;
                }
            }
        }
        return options.get(0);
    }
}
