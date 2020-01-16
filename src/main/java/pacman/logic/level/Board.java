package pacman.logic.level;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;
import pacman.logic.entity.Ghost;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;
import pacman.logic.entity.PowerPellet;
import pacman.logic.entity.Wall;

/**
 * Represents a board with a grid of squares and entities.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Board {

    private final int width;
    private final int height;

    private List<Square> squares;
    private Set<Entity> entities;
    // TODO: Likely remove Level & LevelFactory entirely.
    public PacMan pacman;
    public Set<Ghost> ghosts;
    public Set<Pellet> pellets;
    public Set<PowerPellet> powerPellets;

    /**
     * Creates a board with a specified size.
     *
     * @param width  The width of the board
     * @param height The height of the board
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        this.squares = new ArrayList<Square>(width * height);
        this.entities = new HashSet<Entity>();
        this.ghosts = new HashSet<Ghost>();
        this.pellets = new HashSet<Pellet>();
        this.powerPellets = new HashSet<>();
    }

    /**
     * Gets the square at the given position. If the location is off the board, it wraps around.
     *
     * @param x The x coordinate of the square
     * @param y The y coordinate of the square
     * @return The square at the specified location.
     */
    public @NotNull Square getSquare(int x, int y) {
        return squares.get((int) getPosY(y) * width + (int) getPosX(x));
    }

    /**
     * Gets the square at the given position. If the location is off the board, it wraps around.
     *
     * @param x The x coordinate of the square
     * @param y The y coordinate of the square
     * @return The square at the specified location.
     */
    public @NotNull Square getSquare(double x, double y) {
        return squares.get((int) getPosY(y) * width + (int) getPosX(x));
    }

    /**
     * Adds a square to the board.
     * Should generally only be called by {@link Square#Square(Board, int, int)}.
     *
     * @param square The square to add
     */
    protected void addSquare(@NotNull Square square) {
        // square.getEntities().forEach(this::addEntity);
        // !Square should never contain entities before instantiation.
        squares.add(square);
    }

    /**
     * Adds entity to this board.
     * Should generally only be called by {@link Square#addEntity(Entity)}.
     *
     * @param entity the entity to add.
     * @see Square#addEntity(Entity)
     */
    protected void addEntity(@NotNull Entity entity) {
        if (entity instanceof PacMan) {
            assert (pacman == null);
            pacman = (PacMan) entity;
        } else if (entity instanceof Ghost) {
            ghosts.add((Ghost) entity);
        } else if (entity instanceof Pellet) {
            pellets.add((Pellet) entity);
        } else if (entity instanceof PowerPellet) {
            powerPellets.add((PowerPellet) entity);
        }
        entities.add(entity);
    }

    /**
     * Removes an entity from the board.
     *
     * @param entity The entity to remove
     */
    public void removeEntity(@NotNull Entity entity) {
        if (entity instanceof PacMan) {
            System.err.println("PacMan was removed from the board!");
            pacman = null; //NOPMD Exceptional situation, but not unthinkable.
        } else if (entity instanceof Ghost) {
            ghosts.remove((Ghost) entity);
        } else if (entity instanceof Pellet) {
            pellets.remove((Pellet) entity);
        } else if (entity instanceof PowerPellet) {
            powerPellets.remove((PowerPellet) entity);
        }
        entity.getSquare().removeEntity(entity);
        entities.remove(entity);
    }

    /**
     * Gets the width of the board.
     *
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the board.
     *
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the entities.
     *
     * @return The entities as an iterable
     */
    public @NotNull Iterable<Entity> getEntities() {
        return () -> entities.iterator();
    }

    /**
     * Gets the squares.
     *
     * @return The squares as an iterable
     */
    public @NotNull Iterable<Square> getSquares() {
        return () -> squares.iterator();
    }

    /**
     * Calculates the current score of the player.
     *
     * @return score
     */
    public int computeScore() {
        List<Entity> eatenPellets = entities.stream().filter(e -> !e.isAlive()
                && e instanceof Pellet).collect(Collectors.toList());
        return eatenPellets.size() * 10;
    }

    /**
     * Removes the dead entities from the board.
     */
    public void removeDeadEntities() {
        // We do not remove PacMan
        Set<Entity> dead = entities.stream().filter(e -> !e.isAlive() && !(e instanceof PacMan))
                .collect(Collectors.toSet());
        dead.forEach(this::removeEntity);
    }

    /**
     * Gets x position on the board, with wraparound.
     *
     * @param x The x coordinate
     * @return The x coordinate on the board
     */
    public double getPosX(double x) {
        double x2 = x % width;
        return x2 < 0 ? x2 + width : x2;
    }

    /**
     * Gets y position on the board, with wraparound.
     *
     * @param y The y coordinate
     * @return The y coordinate on the board
     */
    public double getPosY(double y) {
        double y2 = y % height;
        return y2 < 0 ? y2 + height : y2;
    }

    public Set<Ghost> getGhosts() {
        return ghosts;
    }

    public Set<Pellet> getPellets() {
        return pellets;
    }

    /**
     * Gets all the entities, but in the correct order for drawing.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public Iterable<Entity> getEntitiesInOrderForDrawing() {
        Deque<Entity> drawingEntities = new ArrayDeque<>();
        pellets.forEach(drawingEntities::addLast);
        powerPellets.forEach(drawingEntities::addLast);
        ghosts.forEach(drawingEntities::addLast);
        drawingEntities.addLast(pacman);
        for (Entity e: entities) {
            if (e instanceof Wall) {
                drawingEntities.addLast(e);
            }
        }
        return drawingEntities;
    }
}
