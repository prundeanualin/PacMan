package pacman.gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.User;
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
import lombok.Getter;
import lombok.Setter;


public class menuController implements Initializable {

    @FXML
    private StackPane parentContainer;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private Label LabelPacMan;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button profileButton;

    @FXML
    private Button leaderboardButton;

    @Getter
    @Setter
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    @SuppressWarnings("PMD")
    private void loadGameScreen(ActionEvent event) throws IOException {

//                URL url = new File("src/main/resources/menuWindow.fxml").toURL();
////        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menuWindow.fxml"));
//        Parent root= FXMLLoader.load(url);
//        Scene scene = buttonPlay.getScene();
//
//        buttonPlay.setDisable(true);
//
//        root.translateYProperty().set(scene.getHeight());
//        parentContainer.getChildren().add(root);
//
//        Timeline timeline = new Timeline();
//        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
//        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//        timeline.getKeyFrames().add(kf);
//        timeline.setOnFinished(t -> {
//            parentContainer.getChildren().remove(anchorRoot);
//        });
//        timeline.play();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/gameWindow.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    @FXML
    @SuppressWarnings("PMD")
    private void viewProfile(ActionEvent event) throws IOException {
        this.getUser();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/profileController.fxml"));
        Parent root_profile = loader.load();
        menuController controller = (menuController) loader.getController();
        System.out.println(user.getUsername());
        controller.setUser(user);
        Scene scene2 = new Scene(root_profile);
        Stage window2 = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window2.setScene(scene2);
        window2.show();
    }
}
