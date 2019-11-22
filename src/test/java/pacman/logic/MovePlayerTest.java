package pacman.logic;

public class MovePlayerTest extends MoveTest {

    @Override
    public void setup() {
        player = new Player();
        ghost = new Ghost();
        MOVE_TO_GHOST = true;
        MOVE_TO_PLAYER = false;
    }
}
