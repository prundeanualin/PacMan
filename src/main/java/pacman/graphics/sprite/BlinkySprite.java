package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Ghost;

public class BlinkySprite extends Sprite<Ghost>{

    @Override
    public void draw(@NotNull Ghost entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        Image image= new Image(getClass().getResourceAsStream("images/blinky.png"));
        g.drawImage(image, 100, 100, image.getWidth()*0.5, image.getHeight()*0.5);
    }
}
