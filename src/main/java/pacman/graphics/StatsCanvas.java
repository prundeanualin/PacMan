package pacman.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PacmanSprite;
import pacman.logic.entity.Entity;
import pacman.logic.game.Game;
import pacman.logic.game.GameState;
import pacman.logic.level.Board;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class StatsCanvas extends Canvas {

    private GameState state;
    private int score;
    private int lives;

    /**
     * The style to draw in.
     */
    private Style drawStyle = Style.CLASSIC;

    public StatsCanvas(Game game, int width) {
        super(width, 50);

        this.state = game.getState().getValue();
        this.score = game.getPlayer().getScore().get();
        this.lives = game.getPlayer().getLives().get();

        game.getState().addListener((observable, oldVal, newVal) -> setState(newVal));
        game.getPlayer().getScore().addListener((observable, oldVal, newVal) ->
                setScore(newVal.intValue()));
        game.getPlayer().getLives().addListener((observable, oldVal, newVal) ->
                setLives(newVal.intValue()));

        draw();
    }

    private void draw() {
        getGraphicsContext2D().setFill(drawStyle.getBackgroundColor());
        getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());

        getGraphicsContext2D().setFont(Font.font(24));
        getGraphicsContext2D().setFill(drawStyle.getTextColour());
        String text;
        switch (state) {
            case WON:
                text = "You won!";
                break;
            case LOST:
                text = "You lost :(";
                break;
            default:
                text = "Score: " + score;
                break;
        }
        getGraphicsContext2D().fillText(text, 10, getHeight() / 2 + 8);

        getGraphicsContext2D().setFill(drawStyle.getPacmanColour());
        double lifeSize = getHeight() / 2;
        double lifeY = getHeight() / 2 - lifeSize / 2; // NOPMD variable used
        for (int x = 1; x <= lives; x++) {
            getGraphicsContext2D().fillArc(getWidth() - x * lifeSize * 1.2, lifeY, lifeSize, lifeSize,
                    40, 280, ArcType.ROUND);
        }
    }

    private void setState(GameState state) {
        this.state = state;
        draw();
    }

    private void setLives(int lives) {
        this.lives = lives;
        draw();
    }

    private void setScore(int score) {
        this.score = score;
        draw();
    }

}
