package pacman.logic.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.logic.Direction;
import pacman.logic.entity.Entity;
import pacman.logic.entity.Wall;

/**
 * Represents a square on the board.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Square {

    private Board board;
    private Set<Entity> entities;
    private int xs;
    private int ys;
    private boolean solid;

    /**
     * Creates a new empty square and adds it to the board.
     */
    public Square(@NotNull Board board, int xs, int ys) {
        this.board = board;
        this.xs = xs;
        this.ys = ys;
        this.entities = new HashSet<>();
        board.addSquare(this);
    }

    /**
     * Returns the neighbours of this square.
     * Note: Could not be 4 in certain circumstances.
     *
     * @return The neighbours of this square.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // Foreach loop false warning.
    public List<Square> getNeighbours() {
        Square[] surrounding = new Square[4];
        surrounding[0] = getNeighbour(Direction.UP);
        surrounding[1] = getNeighbour(Direction.LEFT);
        surrounding[2] = getNeighbour(Direction.DOWN);
        surrounding[3] = getNeighbour(Direction.RIGHT);

        // Surrounding Squares may include the square itself due to wrap around.
        List<Square> neighbours = new ArrayList<>(4);
        for (Square neighbour : surrounding) {
            if (!neighbour.equals(this)) {
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    public Square getNeighbour(Direction direction) {
        return board.getSquare(xs + direction.getX(), ys + direction.getY());
    }

    /**
     * Return the direction of the other square relative to this square.
     * Note: this is mostly meant for neighbouring squares, other behavior is currently undefined.
     * (Most likely assertion error or illegal argument exception)
     *
     * @param otherSquare - the other square to which we want the direction to point.
     * @return the direction from this to the other square.
     */
    public Direction directionOf(Square otherSquare) {
        int x = otherSquare.xs - this.xs;
        int y = otherSquare.ys - this.ys;

        // Deal with warping.
        if (x > Direction.RIGHT.getX()) {
            x -= board.getWidth();
        } else if (x < Direction.LEFT.getX()) {
            x += board.getWidth();
        }
        if (y > Direction.DOWN.getY()) {
            y -= board.getHeight();
        } else if (y < Direction.UP.getY()) {
            y += board.getHeight();
        }
        assert (x == Direction.LEFT.getX() || x == Direction.UP.getX()
                || x == Direction.RIGHT.getX());
        assert (y == Direction.DOWN.getY() || y == Direction.RIGHT.getY()
                || y == Direction.UP.getY());

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
        this.removeEntity(entity);
        newSquare.addEntity(entity);
    }

    /**
     * Adds an entity to this square, and the board if possible.
     *
     * @param entity The entity to add
     */
    public final void addEntity(@NotNull Entity entity) {
        this.entities.add(entity);

        entity.setSquare(this);
        if (entity.getX() == -1) { // See if position was uninitialized.
            entity.setX(xs + 0.5);
            entity.setY(ys + 0.5);
            board.addEntity(entity);
        }

        assert (!solid); // A square with a solid should not be receiving entities.
        if (entity.isSolid()) {
            solid = true;
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
            solid = false;
        }
    }

    public int getXs() {
        return xs;
    }

    public int getYs() {
        return ys;
    }

    public boolean hasSolid() {
        return solid;
    }

    @Override
    public String toString() {
        return "Square " + xs + ":" + ys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square square = (Square) o;
        return xs == square.xs
                && ys == square.ys
                && solid == square.solid
                && Objects.equals(board, square.board)
                && Objects.equals(entities, square.entities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, entities, xs, ys, solid);
    }
}
