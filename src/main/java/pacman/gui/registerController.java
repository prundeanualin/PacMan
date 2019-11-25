package pacman.gui;

import database.DbConnect;
import database.RegisterDao;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class registerController implements Initializable {

    @FXML
    private TextArea usernameTextBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confPass;

    @FXML
    private Button registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    @SuppressWarnings("PMD")
    private void registerUser(ActionEvent event) throws IOException {
        RegisterDao registerDao = new RegisterDao();
        if (passwordField.getText().equals(confPass.getText())) {
            User user = new User();
            user.setUsername(usernameTextBox.getText());
            user.setPassword(passwordField.getText());
            user.setScore(0);
            if (!registerDao.checkUserAlreadyExists(user)) {
                registerDao.addUser(user);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Username already exists");
                alert.setContentText("Please try again");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Passwords don't match");
            alert.setContentText(passwordField.getText());
            alert.showAndWait();
        }
    }

}
