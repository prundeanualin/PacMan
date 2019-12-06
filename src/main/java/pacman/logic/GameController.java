package pacman.logic;

import database.User;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import pacman.Main;
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

    /**
     * Creates the game controller. Initializes the game, player and levels.
     */
    private GameController() {
        List<Level> levels = new ArrayList<>();
        this.levelFactory = new LevelFactory();
        levels.add(levelFactory.createLevel("level_1"));
        levels.add(levelFactory.createLevel("level_2"));
        //levels.add(levelFactory.createLevel("level_3"));
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
     * @param t The current time since start in seconds.
     */
    public void update(double t) {
        double dt = t - time;
        time = t;
        game.update(dt);
        if (getGame().getLevel().getBoard().checkLevelWon()) {
            pause();
            nextLevel();
        } else {
            labelScore.setText("Score : " + game.getScore());
        }
        canvas.draw(t);
    }

    /**
     * creates a new level for the user who just won the current one.
     */
    public void nextLevel() {
        getGame().advanceLevel();
        getCanvas().levelWon();
    }

    /**
     * Gets the game instance.
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

    /**
     * Sets the label's parameters for displaying score and username.
     * @param scoreLabel the label on which will be displayed
     */
    public void updateLabel(Label scoreLabel) {
        scoreLabel.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        scoreLabel.setFont(new Font(20));
        scoreLabel.setTextFill(Color.WHEAT);
        scoreLabel.setTranslateX(Main.width / 3 * 2);
        scoreLabel.setTranslateY(20);
        scoreLabel.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setUser(User user) {
        getInstance().getGame().setPlayer(user);
    }

    /**
     * Decoupling gui from logical structure.
     */
    public void setUpGui() {
        canvas = new BoardCanvas(getGame().getLevel().getBoard());
        canvas.setHeight(Main.height - 50);
        canvas.setWidth(Main.width);
        canvas.setTranslateY(15);
        labelScore = new Label("Score : ");
        updateLabel(labelScore);
    }

}


