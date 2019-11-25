package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;

public abstract class Ghost extends Entity {

    public Ghost(double x, double y, @NotNull Sprite sprite) {
        super(x, y, sprite);
    }

}
