package pacman.logic.game;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import org.jetbrains.annotations.NotNull;
import pacman.database.User;
import pacman.logic.Player;
import pacman.logic.entity.Entity;
import pacman.logic.entity.Ghost;
import pacman.logic.level.Level;

/**
 * Represents a game with multiple levels.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Game {

    private Player player;
    private static final int lvlMax = 2;

    private List<Level> levels;
    private int currentLevel;
    private ObjectProperty<GameState> state;
    private static double pumpingTime = 8.0;
    private static double time = 0.0;

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
        this.state = new SimpleObjectProperty<>(GameState.READY);
    }

    /**
     * Updates the game.
     * @param dt The time that has passed
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public void update(double dt) {

        if (!isRunning()) {
            return;
        }

        if (getLevel().eatBigOne()) {
            for (Entity e : getLevel().getBoard().getEntities()) {
                if (e instanceof Ghost) {
                    ((Ghost) e).beScared();
                }
            }
        }
        int countEatenG = 0;
        // If frightened timer has expired, ghosts go back to normal chase mode and timer resets.
        if (getLevel().getPacMan().isPumped() && time < pumpingTime) {
            time = time + dt;
            countEatenG = getLevel().getPacMan().checkEatenGhosts();
            player.updateScore(countEatenG * 30);
        } else if (getLevel().getPacMan().isPumped() && time > pumpingTime) {
            time = 0.0;
            getLevel().getPacMan().quitSteroids();
            for (Entity e : getLevel().getBoard().getEntities()) {
                if (e instanceof Ghost && ((Ghost) e).isScared()) {
                    ((Ghost) e).unScare();
                }
            }
        }
        for (Entity entity : getLevel().getBoard().getEntities()) {
            entity.update(dt);
        }
        player.updateScore(getLevel().getBoard().computeScore());
        getLevel().getBoard().removeDeadEntities();
        checkWinLoss();
    }

    private void checkWinLoss() {
        if (getLevel().wasPacManHit()) {
            player.loseLife();
            if (!player.hasLives()) {
                state.set(GameState.LOST);
            } else {
                getLevel().getPacMan().enterImmunity();
                getLevel().revivePlayer();
            }
        }
        if (getLevel().levelWon()) {
            state.set(GameState.WON);
        }
    }

    /**
     * Gets whether the game is running.
     * @return True iff the game is running
     */
    public boolean isRunning() {
        return state.get() == GameState.RUNNING;
    }

    /**
     * Increases the level for the player who just won the last level.
     */
    public void advanceLevel() {
        currentLevel += 1;
        player.newLevel();
    }

    /**
     * Gets the current level.
     * @return The level currently playing
     */
    public @NotNull Level getLevel() {
        return levels.get(currentLevel);
    }

    protected void setPlayer(User user) {
        player.setUsername(user.getUsername());
    }

    public @NotNull ObservableValue<GameState> getState() {
        return state;
    }

    protected void setState(@NotNull GameState state) {
        this.state.set(state);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean won() {
        return currentLevel == lvlMax;
    }

}
