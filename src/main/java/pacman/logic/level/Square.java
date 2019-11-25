package pacman.logic.level;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Square {

    private List<Entity> entities;

    public Square() {
        this.entities = new ArrayList<>();
    }

    protected Square(@NotNull Entity entity) {
        this();
        entities.add(entity);
    }

    public Iterable<Entity> getEntities() {
        return () -> entities.iterator();
    }

    public void moveEntityTo(@NotNull Entity entity, @NotNull Square newSquare) {
        this.entities.remove(entity);
        newSquare.entities.add(entity);
    }

    protected void addEntity(@NotNull Entity entity) {
        this.entities.add(entity);
    }

}
