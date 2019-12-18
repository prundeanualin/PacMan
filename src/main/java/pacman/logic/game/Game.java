package pacman.logic.game;

import pacman.database.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;
import pacman.logic.Player;
import pacman.logic.entity.Entity;
import pacman.logic.level.Level;

import java.util.List;

/**
 * Represents a game with multiple levels.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Game {

    private Player player;

    private List<Level> levels;
    private int currentLevel;
    private ObjectProperty<GameState> state;

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
        for (Entity entity : getLevel().getBoard().getEntities()) {
            entity.update(dt);
        }
        player.updateScore(getLevel().getBoard().computeScore() * 10);
        getLevel().getBoard().removeDeadEntities();
        checkWinLoss();
    }

    private void checkWinLoss() {
        if (getLevel().wasPacManHit()) {
            player.loseLife();
            getLevel().getPacMan().enterImmunity();
            getLevel().revivePlayer();
        }
        if (!player.hasLives()) {
            state.set(GameState.LOST);
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

}
