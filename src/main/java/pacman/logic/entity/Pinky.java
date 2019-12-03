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
        Square pac = pacMan.getSquare();
        Direction pacDir= pacMan.getDirection();
        int x= pac.getX() + pacDir.getX()*4;
        int y= pac.getY() + pacDir.getY()*4;

        if(x> getBoard().getWidth()){
            x= getBoard().getWidth()-1;
        }else if(x<0){
            x=0;
        }
        if(y>getBoard().getHeight()){
            y= getBoard().getHeight()-1;
        }
        else if(y<0){
            y= 0;
        }

        if(getBoard().getSquare(x, y).hasSolid()){
            return getBoard().getSquare(x, y).getNeighbours().get(0);
        }
        return getBoard().getSquare(x, y);
    }
}