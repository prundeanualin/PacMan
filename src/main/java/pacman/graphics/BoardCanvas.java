package pacman.graphics;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
import pacman.Main;
import pacman.graphics.gui.MenuController;
import pacman.graphics.sprite.PacmanSprite;
import pacman.logic.entity.Entity;
import pacman.logic.entity.PacMan;
import pacman.logic.game.GameController;
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
     */
    public BoardCanvas(Board board, int width, int height) {
        super(width, height);
        this.board = board;
        scaleX = width / (double) board.getWidth();
        scaleY = height / (double) board.getHeight();

        long start = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t = (now - start) / 1E9;
                draw(t);
            }
        }.start();
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
            e.getSprite().drawBackground(e, getGraphicsContext2D(), drawStyle, t);
            getGraphicsContext2D().setTransform(new Affine());
        }
        for (Entity e : board.getEntities()) {
            getGraphicsContext2D().scale(scaleX, scaleY);
            getGraphicsContext2D().translate(e.getX(), e.getY());
            if (e instanceof PacMan && !e.isAlive()) {
                end_animations((PacMan) e);
            } else {
                e.getSprite().draw(e, getGraphicsContext2D(), drawStyle, t);
                getGraphicsContext2D().setTransform(new Affine());
            }
        }
    }

    /**
     * Some death animations for PacMan.
     */
    public void end_animations(PacMan player) {
        PacmanSprite ps = (PacmanSprite)player.getSprite();
        ps.animation(getGraphicsContext2D(), drawStyle, player.isAlive());
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
     * and waiting to start the next level/ game is won and button
     * that sends user back to main menu.
     */
    public void createWindow(String msg1, String msg2, int size, boolean menu) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        VBox root = new VBox(70);
        root.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Text text = new Text(msg1);
        text.setFill(Color.WHEAT);
        text.setFont(new Font("Joker", size));
        root.getChildren().add(text);
        Button btn = new Button(msg2);
        btn.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW,
                CornerRadii.EMPTY, Insets.EMPTY)));
        btn.setTextFill(Color.BLACK);
        btn.setOnAction(event -> {
            if (menu) {
                try {
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
                    Parent roots = loader.load();
                    MenuController controller = (MenuController) loader.getController();
                    controller.setProfileDetails(MenuController.user);
                    Scene sc = new Scene(roots);
                    clear();
                    GameController.getInstance().reset();
                    Stage stg = MenuController.stage;
                    stg.setScene(sc);
                    stg.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                setBoard(GameController.getInstance().getGame().getLevel().getBoard());
                stage.close();
                GameController.getInstance().unpause();
            }
        });
        root.getChildren().add(btn);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * After a new level is created, sets that level's board to
     * canvas' board.
     * @param board the new board created for the next level
     */
    public void setBoard(Board board) {
        this.board = board;
        scaleX = Main.width / (double) board.getWidth();
        scaleY = Main.height / (double) board.getHeight();
    }
}
