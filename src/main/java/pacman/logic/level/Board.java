package pacman.logic.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;

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
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        return squares.get(y * width + x);
    }

    protected void setSquare(@NotNull Square square, int x, int y) {
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        square.getEntities().forEach(entities::add);
        squares.set(y * width + x, square);
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

}
