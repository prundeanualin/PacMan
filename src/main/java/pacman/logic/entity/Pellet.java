package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PelletSprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;

public class Pellet extends Entity {

    private static final Sprite<Pellet> SPRITE = new PelletSprite();

    public Pellet(@NotNull Board board, int x, int y) {
        super(board, x + 0.5, y + 0.5, SPRITE);
    }
}
