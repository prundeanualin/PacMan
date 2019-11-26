package pacman.logic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;

import pacman.Main;
import pacman.graphics.BoardCanvas;
import pacman.logic.level.Level;
import pacman.logic.level.LevelFactory;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class GameController {

    private static GameController controller = null;

    public static GameController getInstance() {
        if (controller == null) {
            controller = new GameController();
        }
        return controller;
    }

    private BoardCanvas canvas;
    private Game game;
    private LevelFactory levelFactory;

    private double time;

    private GameController() {
        List<Level> levels = new ArrayList<>();
        this.levelFactory = new LevelFactory();
        levels.add(levelFactory.createLevel("level_1"));
        this.game = new Game(new Player(), levels); //TODO
        this.time = 0.0;
        canvas = new BoardCanvas(getGame().getLevel().getBoard()
                , Main.width, Main.height);
    }

    public void start() {
        long start = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t = (now - start) / 1E9;
                update(t);
            }
        };
        timer.start();
    }

    public void update(double t) {
        double dt = t - time;
        time = t;
        game.update(dt);
        canvas.draw(t);
    }

    public Game getGame() {
        return game;
    }

    public BoardCanvas getCanvas() {
        return canvas;
    }
}


