package controllers;

import game.GameFactory;
import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.stage.Stage;



public class MainMenuController {

    private Stage stage;
    private GameFactory gf;

    @FXML
    public transient Button button;


    public void initialize() {
        button.setVisible(true);
        button.setText("Let's Play !");
    }

    public void setGameFactory(GameFactory gameFactory) {
        gf = gameFactory;
    }


    /**.
     * Listening to clicks on the 'Start Game' button and launching a new game
     */
    @FXML
    @SuppressWarnings("PMD")
    public void onClick() throws IOException {
        // Will be retrieved from the db (or newly created) when logging in/ registering
        String username = "";
        gf.setUserName(username);
        gf.createGame(stage);
    }

    public GameFactory getGf() {
        return gf;
    }

    public void setGf(GameFactory gf) {
        this.gf = gf;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
