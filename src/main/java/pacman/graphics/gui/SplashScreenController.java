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
import javafx.stage.Stage;

public class SplashScreenController implements Initializable {

    @FXML
    Button splashButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Once the player presses 'Play', he is redirected to login page.
     * @param event the click player makes
     * @throws IOException in case it is not recorded
     */
    public void splash(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
