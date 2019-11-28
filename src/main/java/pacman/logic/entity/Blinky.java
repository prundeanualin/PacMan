package pacman.logic.entity;

import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.GameController;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

import javax.xml.stream.events.EntityReference;
import java.util.List;
import java.util.Set;

public class Blinky extends Ghost {

    private static final Sprite<Ghost> sprite = null;

    public Blinky(Board board, Square square) {
        super(board, square, sprite);
    }

    @Override
    Square chooseTarget(List<Square> options) {
        //TODO: calculate distances to pacman (or his square?) from the options, and choice the direction to the option that gives the smallest.
        Direction nextDir=null;
        for (Square s: options){
            Iterable<Entity> it=s.getEntities();
            for(Entity n: it){
                if(n instanceof PacMan){
                    nextDir= square.directionOf(s);
                    return s;
                }
            }
        }
        return options.get(0);
    }
}
