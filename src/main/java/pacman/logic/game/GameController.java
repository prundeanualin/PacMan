package pacman.logic.game;

import pacman.database.User;
import javafx.animation.AnimationTimer;
import pacman.logic.Player;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the game, responsible for starting, pausing, updating and stopping the game.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class GameController {

    /**
     * The game controller singleton.
     */
    private static GameController controller = null;

    /**
     * Gets the GameController instance. There is only one instance.
     * @return The game controller
     */
    public static GameController getInstance() {
        if (controller == null) {
            controller = new GameController();
        }
        return controller;
    }

    private Game game;
    private LevelFactory levelFactory;
    private AnimationTimer timer;
    private User user;

    private double time;

    /**
     * Creates the game controller. Initializes the game, player and levels.
     */
    private GameController() {
        List<Level> levels = new ArrayList<>();
        this.levelFactory = new LevelFactory();
        levels.add(levelFactory.createLevel("level_1"));
        this.game = new Game(new Player(), levels); //TODO
        this.time = 0.0;
    }

    /**
     * Starts the game and game update loop.
     */
    public void start() {
        startTimer();
        game.setState(GameState.RUNNING);
    }

    /**
     * Starts the timer for updating the game.
     */
    protected void startTimer() {
        long start = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t = (now - start) / 1E9;
                update(t);
            }
        };
        timer.start();

    }

    /**
     * Pauses the game.
     */
    public void pause() {
        if (!game.isRunning()) {
            throw new IllegalStateException("Can not pause a game that is not running");
        }
        game.setState(GameState.PAUSED);
        timer.stop();
    }

    /**
     * Unpauses the game.
     */
    public void unpause() {
        if (game.getState().getValue() != GameState.PAUSED) {
            throw new IllegalStateException("Can not unpause a game that is not paused");
        }
        game.setState(GameState.RUNNING);
    }

    /**
     * Updates the game.
     * @param t The current time since start in seconds.
     */
    public void update(double t) {
        double dt = t - time;
        time = t;
        game.update(dt);
    }

    /**
     * Gets the game instance.
     * @return The game
     */
    public Game getGame() {
        return game;
    }

    public void setUser(User user) {
        getInstance().getGame().setPlayer(user);
    }

}


