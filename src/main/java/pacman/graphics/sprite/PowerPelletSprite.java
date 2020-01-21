package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.PowerPellet;

public class PowerPelletSprite extends Sprite<PowerPellet> {

    @Override
    public void draw(@NotNull PowerPellet entity, @NotNull GraphicsContext g,
                     @NotNull Style style, double t) {
        g.setFill(style.getPowerPelletColor());
        g.fillOval(-0.3, -0.3, 0.6, 0.6);
    }
}
