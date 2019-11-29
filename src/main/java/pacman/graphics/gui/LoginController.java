package pacman.graphics.gui;

import database.LoginDao;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private static Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextArea usernameTextArea;

    @FXML
    private static StackPane parentContainer;

    @FXML
    private static AnchorPane anchorRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private void loadRegisterScreen(ActionEvent event) throws IOException {

        /*
        URL url = new File("src/main/resources/menu.fxml").toURL();
        Parent root = FXMLLoader.load(getClass().getResource("../resources/registerWindow.fxml"));
        Parent root= FXMLLoader.load(url);
        Scene scene = registerButton.getScene();

        registerButton.setDisable(true);

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

        Parent root = FXMLLoader.load(getClass().getResource("/views/registerWindow.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void loadMenuScreen(ActionEvent event1) throws IOException {
        /*
        URL url = new File("src/main/resources/menu.fxml").toURL();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Parent root= FXMLLoader.load(url);
        Scene scene = loginButton.getScene();

        loginButton.setDisable(true);

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
        LoginDao loginDao= new LoginDao();
        User user = new User();
        user.setUsername(usernameTextArea.getText());
        user.setPassword(passwordField.getText());
        if(loginDao.attemptLogin(user)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
            Parent root = loader.load();
            MenuController controller = (MenuController) loader.getController();
            controller.setProfileDetails(user);
            Scene scene = new Scene(root);
            Stage window = (Stage) ((javafx.scene.Node) event1.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }  else{
            /*
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Username or password is incorrect");
            alert.setContentText("Please try again");

            alert.showAndWait();
            */
            usernameTextArea.setText(null);
            passwordField.setText(null);
        }

    }

}
