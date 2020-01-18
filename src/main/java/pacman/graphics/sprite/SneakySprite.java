package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Ghost;

public class SneakySprite extends Sprite<Ghost> {
    Image image = new Image(getClass().getResourceAsStream("/images/clyde.png"));

    @Override
    public void draw(@NotNull Ghost entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        g.setFill(style.getClydeColour());
        g.drawImage(image, -0.5, -0.5, 1, 1);

    }
}