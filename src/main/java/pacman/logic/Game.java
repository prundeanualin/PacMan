package pacman.logic;

import database.User;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;
import pacman.logic.level.Level;

/**
 * Represents a game with multiple levels.
 */

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Game {

    private Player player;

    private List<Level> levels;
    private int currentLevel;

    private boolean running = false;

    /**
     * Creates a new game.
     * @param player The player who plays the game
     * @param levels The levels in the game
     */
    public Game(Player player, List<Level> levels) {
        assert !levels.isEmpty();
        this.player = player;
        this.levels = levels;
        this.currentLevel = 0;
    }

    /**
     * Updates the game.
     * @param dt The time that has passed
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public void update(double dt) {
        if (!running) {
            return;
        }
        for (Entity entity : getLevel().getBoard().getEntities()) {
            entity.update(dt);
        }
        player.updateScore(getLevel().getBoard().computeScore() * 10);
        getLevel().getBoard().removeDeadEntities();
    }

    /**
     * Gets whether the game is running.
     * @return True iff the game is running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the running status of the game.
     * @param running Whether the game is running or not
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Increases the level for the player who just won the last level.
     */
    public void advanceLevel() {
        currentLevel += 1;
    }

    /**
     * Gets the current level.
     * @return The level currently playing
     */
    public @NotNull Level getLevel() {
        return levels.get(currentLevel);
    }

    protected int getScore() {
        return player.getScore();
    }

    protected void setPlayer(User user) {
        player.setUsername(user.getUsername());
    }
}
