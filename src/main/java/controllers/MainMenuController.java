package controllers;

import game.GameFactory;
import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.stage.Stage;



public class MainMenuController {

    private Stage stage;
    private GameFactory gameFactory;

    @FXML
    public transient Button button;


    public void initialize() {
        button.setVisible(true);
        button.setText("Let's Play !");
    }

    /**.
     * Listening to clicks on the 'Start Game' button and launching a new game
     */
    @FXML
    @SuppressWarnings("PMD")
    public void onClick() throws IOException {
        // TODO Will be retrieved from the db (or newly created)
        //TODO when logging in/ registering
        String username = "Alin";
        // TODO the level at which the user wants to start playing..
        //  maybe we ll implement some kind of save behaviour,
        //TODO in which, once he reaches a specific level, the user
        // can resume playing from that specific one. This will be stored in db
        int lvlCount = 1;
        gameFactory.setUserName(username);
        gameFactory.setMainMenuController(this);
        gameFactory.createGame(lvlCount);
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public void setGameFactory(GameFactory gameFact) {
        gameFactory = gameFact;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
