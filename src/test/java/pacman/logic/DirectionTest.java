package pacman.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void testInverseDirectionUp() {
        assertEquals(Direction.UP.getInverse(), Direction.DOWN);
    }

    @Test
    public void testInverseDirectionDown() {
        assertEquals(Direction.DOWN.getInverse(), Direction.UP);
    }

    @Test
    public void testInverseDirectionLeft() {
        assertEquals(Direction.LEFT.getInverse(), Direction.RIGHT);
    }

    @Test
    public void testInverseDirectionRight() {
        assertEquals(Direction.RIGHT.getInverse(), Direction.LEFT);
    }

}
