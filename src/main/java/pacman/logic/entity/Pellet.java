package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;

public class Pellet extends Entity {

    public Pellet(double x, double y, @NotNull Sprite sprite) {
        super(x, y, sprite);
    }
}
