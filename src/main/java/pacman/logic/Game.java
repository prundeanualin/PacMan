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
<<<<<<< HEAD
     * Creating a game with its dependencies.
     * @param player the player created from user details
     * @param levels the levels he is able to play
=======
     * Creates a new game.
     * @param player The player who plays the game
     * @param levels The levels in the game
>>>>>>> c0f09407c89fca96c5e2effb10e415bc0bc0b601
     */
    public Game(Player player, List<Level> levels) {
        assert !levels.isEmpty();
        this.player = player;
        this.levels = levels;
        this.currentLevel = 0;
    }

    /**
<<<<<<< HEAD
     * Creating animations with regular time intervals updates around 60 fps.
     * @param dt the time interval
=======
     * Updates the game.
     * @param dt The time that has passed
>>>>>>> c0f09407c89fca96c5e2effb10e415bc0bc0b601
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
