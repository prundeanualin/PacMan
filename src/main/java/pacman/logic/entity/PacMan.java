package pacman.logic.entity;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PacmanSprite;
import pacman.graphics.sprite.Sprite;
import pacman.logic.Direction;
import pacman.logic.level.Board;
import pacman.logic.level.Square;

/**
 * Represents the PacMan entity on the board.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class PacMan extends Entity {

    private static final Sprite<PacMan> SPRITE = new PacmanSprite();

    private boolean immune = false;
    private double immuneTimer = 0.0;

    /**
     * Creates a new PacMan.
     *
     * @param board  The board PacMan is on
     * @param square The square PacMan is on
     */
    public PacMan(@NotNull Board board, Square square) {
        super(board, square, SPRITE);
        direction = Direction.RIGHT;
        Ghost.pacMan = this;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        Set<Entity> collisions = checkCollision();
        if (immune) {
            immuneTimer -= dt;
            immune = immuneTimer > 0.0;
        } else {
            // Set every collided pellet to dead
            collisions.stream().filter(e -> e instanceof Pellet).forEach(e -> e.setAlive(false));
        }
    }

    public boolean isImmune() {
        return immune;
    }

    public void enterImmunity() {
        immune = true;
        immuneTimer = 2.0;
    }

}
