package pacman.graphics.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label userNameLabel;

    @FXML
    private Label scoreLabel;

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") //knwon bug of pmd when declaring variables
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        //Parent root = loader.load(getClass().getClassLoader().getResource("profile.fxml"));
        MenuController controller = (MenuController) loader.getController();
//        User user = controller.getUser();
//        UserDao userDao = new UserDao();
//        userNameLabel.setText(userDao.getUsernameFromDatabase(user));

    }
    @FXML
    private void Display(ActionEvent event) throws Exception{

    }
}
