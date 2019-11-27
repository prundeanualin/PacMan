package pacman.logic.entity;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PelletSprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.level.Board;

/**
 * Represents a pellet PacMan can eat.
 */
public class Pellet extends Entity {

    private static final Sprite<Pellet> SPRITE = new PelletSprite();

    /**
     * Creates a pellet.
     * @param board The board the pellet is on
     * @param x The pellet's x coordinate
     * @param y The pellet's y coordinate
     */
    public Pellet(@NotNull Board board, int x, int y) {
        super(board, x + 0.5, y + 0.5, SPRITE);
    }
}
