package pacman.logic;

/**.
 * Class for keeping track of directions of different sprites,
 * the orientation of their animation and their future moves.
 */
public enum Direction {

    /**.
     * If the difference between the previous coordinate and the new one is
     * negative on Y-axis and constant on X-axis, then the sprite has
     * moved up
     */
    UP(0, -1, Math.PI / 2),

    /**.
     * If the difference between the previous coordinate and the new one is
     * positive on Y-axis and constant on X-axis, then the sprite has
     * moved down
     */
    DOWN(0, 1, 3 * Math.PI / 2),

    /**.
     * If the difference between the previous coordinate and the new one is
     * negative on X-axis and constant on Y-axis, then the sprite has
     * moved left
     */
    LEFT(-1, 0, Math.PI),

    /**.
     * If the difference between the previous coordinate and the new one is
     * positive on X-axis and constant on Y-axis, then the sprite has
     * moved down
     */
    RIGHT(1, 0, 0.0);

    private final int deltaX;

    private final int deltaY;

    private final double rotation;

    Direction(int deltaX, int deltaY, double rotation) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.rotation = rotation;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public double getRotation() {
        return rotation;
    }
}
