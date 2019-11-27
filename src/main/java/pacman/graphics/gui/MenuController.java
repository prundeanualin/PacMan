package pacman.graphics.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuController implements Initializable {

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
        /*
        URL url = new File("src/main/resources/menu.fxml").toURL();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Parent root= FXMLLoader.load(url);
        Scene scene = buttonPlay.getScene();

        buttonPlay.setDisable(true);

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
         */

        Parent root = FXMLLoader.load(getClass().getResource("/views/game.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

}
