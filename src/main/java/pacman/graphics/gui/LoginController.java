package pacman.graphics.gui;

import database.LoginDao;
import database.User;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private static Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField; //NOPMD it's a gui object, no need to set and get it

    @FXML
    private TextField usernameTextField; //NOPMD it's a gui object, no need to set and get it

    @FXML
    private static StackPane parentContainer;

    @FXML
    private static AnchorPane anchorRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private void loadRegisterScreen(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/views/registerWindow.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void loadMenuScreen(ActionEvent event1) throws IOException {

        LoginDao loginDao = new LoginDao(); //NOPMD no error, just a DB variable used
        // for verifying user's identity.
        User user = new User();
        user.setUsername(usernameTextField.getText());
        user.setPassword(passwordField.getText());
        goToMenu(event1, user);
        /*       if (loginDao.attemptLogin(user)) {
                    goToMenu(event1);
                }  else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Username or password is incorrect");
                    alert.setContentText("Please try again");

                    alert.showAndWait();

                    usernameTextField.setText(null);
                    passwordField.setText(null);}
        */

    }

    /**
     * Switches scene to the Main Menu one.
     * @throws IOException in case it can't find the MainMenu controller
     */
    public void goToMenu(ActionEvent event1, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
        Parent root = loader.load();
        MenuController controller = (MenuController) loader.getController();
        controller.setProfileDetails(user);
        Scene scene = new Scene(root);
        Stage window = (Stage) ((javafx.scene.Node) event1.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
