package pacman.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Ghost;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Not a bean.
public class BlinkySprite extends GhostSprite {
    Image image = new Image(getClass().getResourceAsStream("/images/blinky.png"));

    @Override
    public void drawBackground(@NotNull Ghost entity, @NotNull GraphicsContext g,
                               @NotNull Style style, double t) {
        drawHome(entity, g, style.getBlinkyColour(), style.getBackgroundColor());
    }

    @Override
    public void draw(@NotNull Ghost entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {
        g.setFill(style.getBlinkyColour());
        g.drawImage(image, -0.5, -0.5, 1, 1);
    }
}
