package pacman.graphics.gui;

import database.User;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pacman.graphics.GameView;

import pacman.Main;
import pacman.graphics.BoardCanvas;
import pacman.logic.Direction;
import pacman.logic.game.GameController;
import pacman.logic.entity.PacMan;

public class MenuController implements Initializable {

    public static User user;
    private Scene scene;
    private Stage stage;

    @FXML
    private Label userDetails; //NOPMD no need for having set/get for thi gui element

    @FXML
    private StackPane parentContainer;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private Label labelPacMan;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button profileButton;

    @FXML
    private Button leaderboardButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    //known bug of pmd when using variable declaration
    @FXML
    private void loadGameScreen(ActionEvent event)
            throws IOException {

        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        GameView root = new GameView(GameController.getInstance().getGame(), 800, 800);
        GameController.getInstance().setUser(user);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        startGame();

    }

    /**
     * Starting the game window and timers.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void startGame() {
        GameController.getInstance().start();

        scene.setOnKeyPressed(e -> {
            PacMan pm = GameController.getInstance().getGame().getLevel().getPacMan();
            switch (e.getCode()) {
                case UP:
                    pm.setNextDirection(Direction.UP);
                    break;
                case DOWN:
                    pm.setNextDirection(Direction.DOWN);
                    break;
                case LEFT:
                    pm.setNextDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    pm.setNextDirection(Direction.RIGHT);
                    break;
                default:
                    // NOOP
            }
        });

    }

    /**
     * Displaying the user profile info (username) and his current score.
     * @param us the user
     */
    public void setProfileDetails(User us) {
        user = us;
        userDetails.setText("User: " + user.getUsername()
                + "\n" + "High score: " + user.getScore());
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MenuController.user = user;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the label's parameters for displaying score and username.
     * @param scoreLabel the label on which will be displayed
     */
    private void updateLabel(Label scoreLabel) {
        scoreLabel.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        scoreLabel.setFont(new Font(20));
        scoreLabel.setTextFill(Color.WHEAT);
        scoreLabel.setTranslateX(Main.width / 3 * 2);
        scoreLabel.setTranslateY(20);
        scoreLabel.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
