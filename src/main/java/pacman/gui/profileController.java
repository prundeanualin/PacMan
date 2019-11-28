package pacman.gui;

import controllers.MainMenuController;
import database.User;
import database.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class profileController implements Initializable {

    @FXML
    private Label userNameLabel;

    @FXML
    private Label scoreLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        //Parent root = loader.load(getClass().getClassLoader().getResource("profile.fxml"));
        MainMenuController controller = (MainMenuController) loader.getController();
//        User user = controller.getUser();
//        UserDao userDao = new UserDao();
//        userNameLabel.setText(userDao.getUsernameFromDatabase(user));

    }
    @FXML
    private void Display(ActionEvent event) throws Exception{

    }
}
