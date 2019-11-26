package pacman.logic.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;
import pacman.logic.entity.Pellet;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Board {

    private final int width;
    private final int height;

    private List<Square> squares;
    private Set<Entity> entities;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        this.squares = new ArrayList<>(width * height);
        this.entities = new HashSet<>();
    }

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

    protected void addSquare(@NotNull Square square) {
        square.getEntities().forEach(entities::add);
        squares.add(square);
    }

    protected void removeEntity(@NotNull Entity entity) {
        entity.getSquare().removeEntity(entity);
        entities.remove(entity);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public @NotNull Iterable<Entity> getEntities() {
        return () -> entities.iterator();
    }

    public @NotNull Iterable<Square> getSquares() {
        return () -> squares.iterator();
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // boolean won is a check for having any remaining pellets
    public boolean checkLevelWon() {
        List<Entity> pellets = entities.stream().filter(e -> e instanceof Pellet).collect(Collectors.toList());
        boolean won = true;
        for(Entity e: pellets) {
            won = !e.isAlive();
        }
        return won;
    }

    public int computeScore() {
        List<Entity> eatenPellets = entities.stream().filter(e -> !e.isAlive() && e instanceof Pellet).collect(Collectors.toList());
        return eatenPellets.size();
    }

    public void removeDeadEntities() {
        Set<Entity> dead = entities.stream().filter(e -> !e.isAlive()).collect(Collectors.toSet());
        dead.forEach(this::removeEntity);
    }

}
