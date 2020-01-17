package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PowerPelletSprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

public class PowerPellet extends Entity {

    private static final Sprite<PowerPellet> SPRITE = new PowerPelletSprite();

    public PowerPellet(@NotNull Board board, Square square) {
        super(board, square, SPRITE);
    }

    @Override
    protected boolean isWithinBound(double dx, double dy) {
        return dx * dx + dy * dy < 0.4;
    }

    @Override
    public void update(double dtSmall) {
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
