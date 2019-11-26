package pacman.logic.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;

/**
 * Represents a board with a grid of squares and entities.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Board {

    private final int width;
    private final int height;

    private List<Square> squares;
    private Set<Entity> entities;

    /**
     * Creates a board with a specified size.
     * @param width The width of the board
     * @param height The height of the board
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        this.squares = new ArrayList<>(width * height);
        this.entities = new HashSet<>();
    }

    /**
     * Gets the square at the given position. If the location is off the board, it wrap around.
     * @param x The x coordinate of the square
     * @param y The y coordinate of the square
     * @return The square at the specified location.
     */
    public @NotNull Square getSquare(int x, int y) {
        if (x < 0) {
            x += width;
        } else if (x >= width) {
            x -= width;
        }
        if (y < 0) {
            y += height;
        } else if (y >= height) {
            y -= height;
        }
        return squares.get(y * width + x);
    }

    /**
     * Adds a square to the board.
     * @param square The square to add
     */
    protected void addSquare(@NotNull Square square) {
        square.getEntities().forEach(entities::add);
        squares.add(square);
    }

    /**
     * Removes an entity from the board.
     * @param entity The entity to remove
     */
    protected void removeEntity(@NotNull Entity entity) {
        entity.getSquare().removeEntity(entity);
        entities.remove(entity);
    }

    /**
     * Gets the width of the board.
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the board.
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the entities.
     * @return The entities as an iterable
     */
    public @NotNull Iterable<Entity> getEntities() {
        return () -> entities.iterator();
    }

    /**
     * Gets the squares.
     * @return The squares as an iterable
     */
    public @NotNull Iterable<Square> getSquares() {
        return () -> squares.iterator();
    }

    /**
     * Removes the dead entities from the board.
     */
    public void removeDeadEntities() {
        Set<Entity> dead = entities.stream().filter(e -> !e.isAlive()).collect(Collectors.toSet());
        dead.forEach(this::removeEntity);
    }

}
