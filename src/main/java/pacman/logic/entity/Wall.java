package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.Sprite;

public class Wall extends Entity{

    public Wall(double x, double y, @NotNull Sprite sprite) {
        super(x, y, sprite);
    }
}
