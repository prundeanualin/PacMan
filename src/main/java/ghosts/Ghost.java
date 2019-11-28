package ghosts;

import board.Direction;

import java.util.HashMap;
import java.util.Map;

//TODO not ready yet, still needs implementation of
// behaviour and basic structure, this is just a mainframe
public abstract class Ghost {

    // Map structure to hold every animation for each direction(up, down, left, right)
    private final Map<Direction, Sprite> spriteMap;
    // The speed at which the ghost moves on the board
    private final int movingSpeed;

    public Ghost(Map<Direction, Sprite> sprites, int movs) {
        spriteMap = new HashMap<Direction, Sprite>();
        movingSpeed = movs;
    }

    public Map<Direction, Sprite> getSpriteMap() {
        return spriteMap;
    }

    public int getMovingSpeed() {
        return movingSpeed;
    }
}
