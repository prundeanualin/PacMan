package pacman.logic.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.logic.Direction;
import pacman.logic.entity.Entity;

/**
 * Represents a square on the board.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Square {

    private Board board;
    private Set<Entity> entities;
    private int x;
    private int y;
    private boolean hasSolid;

    /**
     * Creates a new empty square.
     */
    public Square(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.entities = new HashSet<>();
    }

    /**
     * Creates a new square containing the entity.
     *
     * @param entity The entity in the square
     */
    protected Square(@NotNull Entity entity, Board board, int x, int y) {
        this(board, x, y);
        addEntity(entity);
    }

    /**
     * Returns the neighbours of this square.
     * Note: Could not be 4 in certain circumstances.
     *
     * @return The neighbours of this square.
     */
    public List<Square> getNeighbours() {
        Square[] surrounding = new Square[4];
        surrounding[0] = getNeighbour(Direction.UP);
        surrounding[1] = getNeighbour(Direction.LEFT);
        surrounding[2] = getNeighbour(Direction.DOWN);
        surrounding[3] = getNeighbour(Direction.RIGHT);

        // Surrounding Squares may include the square itself due to wrap around.
        List<Square> neighbours = new ArrayList<>(4);
        for (Square neighbour : surrounding) {
            if (neighbour != this) {
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    public Square getNeighbour(Direction direction) {
        return board.getSquare(x + direction.getDeltaX(), y + direction.getDeltaY());
    }

    /**
     * Return the direction of the other square relative to this square.
     * Note: this is mostly meant for neighbouring squares, other behavior is currently undefined. (Most likely assertion error)
     *
     * @param otherSquare - the other square to which we want the direction to point.
     * @return the direction from this to the other square.
     */
    public Direction directionOf(Square otherSquare) {
        int x = otherSquare.x - this.x;
        int y = otherSquare.y - this.y;

        if (x > 1) x -= board.getWidth();
        else if (x < 1) x += board.getWidth();
        if (y > 1) y -= board.getHeight();
        else if (y < 1) y += board.getHeight();
        assert (x == -1 || x == 0 || x == 1);
        assert (y == -1 || y == 0 || y == 1);

        return Direction.getDirection(x, y);
    }

    /**
     * Gets the entities that are in this square.
     *
     * @return The entities as an iterable.
     */
    public Iterable<Entity> getEntities() {
        return () -> entities.iterator();
    }

    /**
     * Moves an entity from this square to another square.
     *
     * @param entity    The entity to move
     * @param newSquare The square to move the entity to
     */
    public void moveEntity(@NotNull Entity entity, @NotNull Square newSquare) {
        this.entities.remove(entity);
        newSquare.entities.add(entity);
    }

    /**
     * Adds an entity to this square.
     *
     * @param entity The entity to add
     */
    protected void addEntity(@NotNull Entity entity) {
        this.entities.add(entity);
        assert (!hasSolid); // A square with a solid should not be receiving entities.
        if (entity.isSolid()) {
            hasSolid = true;
        }
    }

    /**
     * Removes an entity from this square.
     *
     * @param entity The entity to remove
     */
    protected void removeEntity(@NotNull Entity entity) {
        this.entities.remove(entity);
        if (entity.isSolid()) { // Squares implicitly never contain more than 1 solid.
            hasSolid = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasSolid() {
        return hasSolid;
    }
}
