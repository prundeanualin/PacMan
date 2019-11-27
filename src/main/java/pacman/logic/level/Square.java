package pacman.logic.level;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;

/**
 * Represents a square on the board.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Square {

    private Set<Entity> entities;

    /**
     * Creates a new empty square.
     */
    public Square() {
        this.entities = new HashSet<>();
    }

    /**
     * Creates a new square containing the entity.
     * @param entity The entity in the square
     */
    protected Square(@NotNull Entity entity) {
        this();
        entities.add(entity);
    }

    /**
     * Gets the entities that are in this square.
     * @return The entities as an iterable.
     */
    public Iterable<Entity> getEntities() {
        return () -> entities.iterator();
    }

    /**
     * Moves an entity from this square to another square.
     * @param entity The entity to move
     * @param newSquare The square to move the entity to
     */
    public void moveEntityTo(@NotNull Entity entity, @NotNull Square newSquare) {
        this.entities.remove(entity);
        newSquare.entities.add(entity);
    }

    /**
     * Adds an entity to this square.
     * @param entity The entity to add
     */
    protected void addEntity(@NotNull Entity entity) {
        this.entities.add(entity);
    }

    /**
     * Removes an entity from this square.
     * @param entity The entity to remove
     */
    protected void removeEntity(@NotNull Entity entity) {
        this.entities.remove(entity);
    }

}
