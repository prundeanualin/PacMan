package pacman.logic;

/**
 * .
 * Class for keeping track of directions of different sprites,
 * the orientation of their animation and their future moves.
 */
public enum Direction {

    /**
     * .
     * If the difference between the previous coordinate and the new one is
     * negative on Y-axis and constant on X-axis, then the sprite has
     * moved up
     */
    UP(0, -1, Math.PI / 2),

    /**
     * .
     * If the difference between the previous coordinate and the new one is
     * negative on X-axis and constant on Y-axis, then the sprite has
     * moved left
     */
    LEFT(-1, 0, Math.PI),

    /**
     * .
     * If the difference between the previous coordinate and the new one is
     * positive on Y-axis and constant on X-axis, then the sprite has
     * moved down
     */
    DOWN(0, 1, 3 * Math.PI / 2),

    /**
     * .
     * If the difference between the previous coordinate and the new one is
     * positive on X-axis and constant on Y-axis, then the sprite has
     * moved down
     */
    RIGHT(1, 0, 0.0);

    private final int x;

    private final int y;

    private final double rotation;

    Direction(int x, int y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Direction getInverse() {
        return Direction.values()[(ordinal() + 2) % values().length];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    /**
     * Return the Direction given its x & y equivalent.
     *
     * @param x - x equivalent
     * @param y - y equivalent
     * @return the direction represented by (x,y).
     */
    public static Direction getDirection(int x, int y) {
        if (x == Direction.RIGHT.x) {
            return Direction.RIGHT;
        }
        if (x == Direction.LEFT.x) {
            return Direction.LEFT;
        }
        if (y == Direction.UP.y) {
            return Direction.UP;
        }
        if (y == Direction.DOWN.y) {
            return Direction.DOWN;
        }
        throw new IllegalArgumentException("Direction (" + x + ":" + y + ") does not exist.");
    }
}
