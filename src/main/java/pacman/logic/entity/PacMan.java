package pacman.logic.entity;

import java.util.List;
import java.util.Set;
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
    private static final double immuneTime = 2.0;
    private boolean pumped = false;
    private boolean drunk = false;
    private double drunkTimer = 0;
    public static final double drunkTime = 4.0;

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
            Set<Entity> collided = checkCollision();
            for (Entity e : collided) {
                if (e instanceof Pellet) {
                    e.setAlive(false);
                } else if (e instanceof Bottle) {
                    drunk = true;
                    e.setAlive(false);
                }
            }
        }
        if (drunk) {
            drunkTimer -= dt;
            drunk = drunkTimer > 0.0;
        }
    }

    /**
     * After eating a powerPellet, PacMAn enters a 'pumped' state and
     * is able to return the favor and eat any of the ghosts for bonus points.
     *
     * @return true if it did collide with such a magic pellet/ false otherwise.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public boolean pumpedWithPower() {
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
                ((Ghost) e).justEaten();
            }
        }
        return count;
    }

    /**
     * Whether PacMan is immune and cannot collide with entities.
     * (except walls of course)
     *
     * @return whether PacMan is immune
     */
    public boolean isImmune() {
        return immune;
    }

    /**
     * Makes PacMan immune to collisions with anything except walls.
     * This uses a timer and thus is temporary.
     */
    public void enterImmunity() {
        immune = true;
        immuneTimer = immuneTime;
    }

    /**
     * Returns whether or not PacMan is pumped/on steroids.
     * If this is the case he can eat ghosts.
     *
     * @return if PacMan is pumped/on steroids
     */
    public boolean isOnSteroids() {
        return pumped;
    }

    /**
     * Stop PacMan from being pumped.
     * So he can no longer eat ghosts.
     */
    public void quitSteroids() {
        pumped = false;
    }

    /**
     * Returns if pacman is drunk and thus input is reversed.
     *
     * @return if pacman is drunk
     */
    public boolean isDrunk() {
        return drunk;
    }

}
