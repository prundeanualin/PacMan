package pacman.graphics.gui;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import pacman.database.User;
import pacman.graphics.GameView;
import pacman.logic.Direction;
import pacman.logic.entity.PacMan;
import pacman.logic.game.GameController;
import pacman.logic.game.GameState;

public class MenuController implements Initializable {

    private GameView gameView;
    private Scene scene;    //NOPMD no need for get/set for this one;
    // it is just for ease of use
    public static Stage stage;
    public static User user;

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
    private void loadGameScreen(ActionEvent event) {
        startGame(event);
    }

    /**
     * Starting the game window and timers.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void startGame(ActionEvent event) {

        stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        gameView = new GameView(GameController.getInstance().getGame(), 790, 720);
        GameController.getInstance().setUser(user);
        scene = new Scene(gameView);
        stage.setScene(scene);
        stage.show();

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

        GameController.getInstance().getGame().getState().addListener((ob, o, n) -> {
            if (n == GameState.WON || n == GameState.LOST) {
                gameView.getBoardCanvas().stopGame();
                GameController.getInstance().stop();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameFinished(n);
            }
        });

    }

    private void gameFinished(GameState state) {
        if (state == GameState.WON && !GameController.getInstance().getGame().won()) {
            createDialogueWindow("!! Level Won !!", "Next Level", false);
        } else if (state == GameState.WON && GameController.getInstance().getGame().won()) {
            createDialogueWindow("!! GAME WON !!", " Go to Main Menu", true);
        } else {
            createDialogueWindow("GAME LOST :(", "Got to Main Menu", true);
        }
        //try {
        //    Parent root = FXMLLoader.load(getClass().getResource("/views/leaderboard.fxml"));
        //    window.getScene().setRoot(root);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    private void createDialogueWindow(String msg1, String msg2, boolean menu) {
        Stage stg = new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.initStyle(StageStyle.UNDECORATED);
        VBox root = new VBox(70);
        root.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Text text = new Text(msg1);
        text.setFill(Color.WHEAT);
        text.setFont(new Font("Joker", 27));
        root.getChildren().add(text);
        Button btn = new Button(msg2);
        btn.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW,
                CornerRadii.EMPTY, Insets.EMPTY)));
        btn.setTextFill(Color.BLACK);
        btn.setOnAction(event -> {
            if (menu) {
                try {
                    stg.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
                    Parent roots = loader.load();
                    MenuController controller = loader.getController();
                    controller.setProfileDetails(MenuController.user);
                    Scene sc = new Scene(roots);
                    GameController.getInstance().reset();
                    stage.setScene(sc);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                GameController.getInstance().nextLevel();
                gameView.getBoardCanvas().setBoard(GameController
                        .getInstance().getGame().getLevel().getBoard());
                stg.close();
                GameController.getInstance().start();
                gameView.getBoardCanvas().start();
            }
        });
        root.getChildren().add(btn);
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.show();
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

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
}
