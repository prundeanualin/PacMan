package pacman.logic;

public class MoveGhostTest extends MoveTest {
    @Override
    public void setup() {
        MOVE_TO_GHOST = false;
        MOVE_TO_PLAYER = true;
    }
}
