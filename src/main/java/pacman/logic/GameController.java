package pacman.logic;

import database.User;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

import pacman.graphics.BoardCanvas;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;

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
     *
     * @return The game controller
     */
    public static GameController getInstance() {
        if (controller == null) {
            controller = new GameController();
        }
        return controller;
    }

    private BoardCanvas canvas;
    private Game game;
    private LevelFactory levelFactory;
    private AnimationTimer timer;
    private Label labelScore;
    private User user;

    private double time;
    private boolean started = false;
    private final double MAX_TIME = 0.5;

    /**
     * Creates the game controller. Initializes the game, player and levels.
     */
    private GameController() {
        List<Level> levels = new ArrayList<>();
        this.levelFactory = new LevelFactory();
        levels.add(levelFactory.createLevel("level_1"));
        levels.add(levelFactory.createLevel("level_2"));
        this.game = new Game(new Player(), levels);
        this.time = 0.0;
    }

    /**
     * Starts the game and game update loop.
     */
    public void start() {
        startTimer();
        game.setRunning(true);
        started = true;
    }

    /**
     * Starts the timer for updating the game.
     */
    protected void startTimer() {
        long start = System.nanoTime(); //NOPMD no reasonable rule violations
        if (timer == null) {
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    double t = (now - start) / 1E9;
                    update(t);
                }
            };
        }
        timer.start();
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        if (!started) {
            throw new IllegalStateException("Can not pause a game that is not started");
        }
        game.setRunning(false);
        timer.stop();
    }

    /**
     * Unpauses the game.
     */
    public void unpause() {
        if (!started) {
            throw new IllegalStateException("Can not unpause a game that is not started");
        }
        game.setRunning(true);
        timer.start();
    }

    /**
     * Updates the game.
     *
     * @param newTime The current time since start in seconds.
     */
    protected void update(double newTime) {
        double dt = Math.min(newTime - time, MAX_TIME); // In exceptional cases use MAX_TIME.
        if (dt < 0) dt = MAX_TIME; // overflow, if possible.
        time = newTime;
        game.update(dt);
        if (getGame().getLevel().checkLevelWon()) {
            pause();
            if (getGame().won(1)) {
                getCanvas().end_animations(true);
                getCanvas().createWindow("!GAME WON!", "Go to Main Menu", 20, true);
            } else {
                nextLevel();
            }
        } else if (getGame().getLevel().checkLevelLost()) {
            pause();
            getCanvas().end_animations(false);
            getCanvas().createWindow("YOU LOST :(", "Go to Main Menu", 20, true);
        } else {
            if (labelScore != null) {
                labelScore.setText("Score : " + game.getScore());
            }
        }
        getCanvas().draw(newTime);
    }

    /**
     * creates a new level for the user who just won the current one.
     */
    public void nextLevel() {
        getGame().advanceLevel();
        getCanvas().createWindow("LEVEL WON !", "Start Next Level", 30, false);
    }

    /**
     * Gets the game instance.
     *
     * @return The game
     */
    public Game getGame() {
        return game;
    }

    public BoardCanvas getCanvas() {
        return canvas;
    }

    public Label getScoreLabel() {
        return labelScore;
    }

    public void setUser(User user) {
        getInstance().getGame().setPlayer(user);
    }


    public void setTimer(AnimationTimer at) {
        timer = at;
    }

    public void setGame(Game g) {
        game = g;
    }

    public void setUpGui(BoardCanvas bc, Label l) {
        canvas = bc;
        labelScore = l;
    }
}


