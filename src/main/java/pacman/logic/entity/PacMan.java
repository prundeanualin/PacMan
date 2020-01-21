package pacman.logic.entity;

import java.util.List;
import java.util.stream.Collectors;

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
public class PacMan extends MovingEntity {

    private static final Sprite<PacMan> SPRITE = new PacmanSprite();

    private boolean immune = false;
    private double immuneTimer = 0.0;
    private boolean pumped = false;

    /**
     * Creates a new PacMan.
     *
     * @param board  The board PacMan is on
     * @param square The square PacMan is on
     */
    public PacMan(@NotNull Board board, Square square) {
        super(board, square, SPRITE);
        direction = Direction.RIGHT;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (immune) {
            immuneTimer -= dt;
            immune = immuneTimer > 0.0;
        } else {
            // Set every collided pellet to dead
            checkCollision().stream().filter(e -> e instanceof Pellet)
                    .forEach(e -> e.setAlive(false));
        }
    }

    /**
     * After eating a powerPellet, PacMAn enters a 'pumped' state and
     * is able to return the favor and eat any of the ghosts for bonus points.
     * @return true if it did collide with such a magic pellet/ false otherwise.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public boolean enterPumped() {
        List<Entity> eatenPowerPellets = checkCollision().stream()
                .filter(e -> e instanceof PowerPellet).collect(Collectors.toList());
        if (!eatenPowerPellets.isEmpty() && !immune) {
            pumped = true;
            for (Entity e : eatenPowerPellets) {
                e.setAlive(false);
            }
            return true;
        }
        return false;
    }

    /**
     * Check if pacman collided with any scared ghosts while
     * in pumped up mode and return nr of such ghosts.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public int checkEatenGhosts() {
        int count = 0;
        List<Entity> eatenGhosts = checkCollision().stream()
                .filter(e -> e instanceof Ghost && ((Ghost) e).isScared())
                .collect(Collectors.toList());
        if (!eatenGhosts.isEmpty()) {
            for (Entity e : eatenGhosts) {
                count++;
                ((Ghost)e).justEaten();
            }
        }
        return count;
    }

    public boolean isImmune() {
        return immune;
    }

    public void enterImmunity() {
        immune = true;
        immuneTimer = 2.0;
    }

    public boolean isPumped() {
        return pumped;
    }

    public void exitPumped() {
        pumped = false;
    }

}
