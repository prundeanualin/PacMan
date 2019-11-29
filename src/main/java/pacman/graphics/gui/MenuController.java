package pacman.graphics.gui;

import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pacman.logic.Direction;
import pacman.logic.GameController;
import pacman.logic.entity.PacMan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private User user;

    @FXML
    private Label userDetails;

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

    @FXML
    private void loadGameScreen(ActionEvent event) throws IOException {


//        Parent root = FXMLLoader.load(getClass().getResource("/views/gameWindow.fxml"));
        VBox root = new VBox();
//        root.getChildren().add(GameController.getInstance().getScoreLabel());
        root.getChildren().add(GameController.getInstance().getCanvas());
        Scene scene = new Scene(root);
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
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

    }

    public void setProfileDetails(User us) {
        user = us;
        userDetails.setText("User: " + user.getUsername() + "\n" + "High score: " + user.getScore());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
