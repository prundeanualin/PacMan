package pacman.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.jetbrains.annotations.NotNull;
import pacman.logic.GameController;
import pacman.logic.entity.Entity;
import pacman.logic.level.Board;

/**
 * Class for drawing the game board with everything on it.
 *
 * @author Ruben
 */// entities has access methods (though PMD does not recognize them),
// additionally class is not a bean.

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class BoardCanvas extends Canvas {

    /**
     * The style to draw in.
     */
    private Style drawStyle = Style.CLASSIC;

    /**
     * The board that should be drawn.
     */
    private Board board;

    private double scaleX;
    private double scaleY;

    /**
     * Creates a new board canvas with specified dimensions.
     *
     * @param width The width of the canvas in pixels
     * @param height The height of the canvas in pixels
     */
    public BoardCanvas(Board board, int width, int height) {

        this.board = board;
        scaleX = width / (double) board.getWidth();
        scaleY = height / (double) board.getHeight();
    }

    /**
     * Clears the canvas with the background colour.
     */
    private void clear() {
        Color bg = drawStyle.getBackgroundColor();
        getGraphicsContext2D().setFill(new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 1.0));
        getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());
    }


    /**
     * Draws everything on the canvas.
     * @param t The time since started in seconds.
     */
    @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "unchecked"})
    /*
     * known bug of pmd with foreach loops
     * it is always safe to draw an entity using its own sprite
     */
    public void draw(double t) {
        /*
         * Suppress warnings:
         *  - known bug of pmd with foreach loops
         *  - it is always safe to draw an entity using its own sprite
         */
        clear();
        getGraphicsContext2D().setLineWidth(1 / scaleX);
        for (Entity e : board.getEntities()) {
            getGraphicsContext2D().scale(scaleX, scaleY);
            getGraphicsContext2D().translate(e.getX(), e.getY());
            e.getSprite().draw(e, getGraphicsContext2D(), drawStyle, t);
            getGraphicsContext2D().setTransform(new Affine());
        }
    }

    /**
     * Sets the style that is used to draw with.
     *
     * @param drawStyle The new draw style
     */
    public void setDrawStyle(@NotNull Style drawStyle) {
        this.drawStyle = drawStyle;
    }

    /**
     * Gets the style that is used to draw with.
     * @return The current draw style
     */
    public Style getDrawStyle() {
        return drawStyle;
    }

    /**
     * Displaying a dialog window for announcing that the level is won
     * and waiting to start the next level.
     */
    public void levelWon() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        VBox root = new VBox(70);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Text text = new Text("LEVEL WON !!");
        text.setFill(Color.WHEAT);
        text.setFont(new Font("Joker", 30));
        root.getChildren().add(text);
        Button btn = new Button("Start Next Level");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setBoard(GameController.getInstance().getGame().getLevel().getBoard());
                dialog.close();
                GameController.getInstance().unpause();
            }
        });
        root.getChildren().add(btn);
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
