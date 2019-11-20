import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.stage.Stage;


public class MainMenuController {

    private GameFactory gf;
    private Stage stage;

    @FXML
    public transient Button button;


    public void initialize() {
        button.setVisible(true);
        button.setText("Let's Play !");
    }

    public void setGameFactory(GameFactory gameFactory, Stage stage1) {
        gf = gameFactory;
        stage = stage1;
    }


    /**.
     * Listening to clicks on the 'Start Game' button and launching a new game
     */
    @FXML
    @SuppressWarnings("PMD")
    public void onClick() {
        try {
            Game game = gf.createGame();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Board.fxml"));
            Parent parent = loader.load();
            Scene boardScene = new Scene(parent);
            stage.setScene(boardScene);
            BoardController bc = loader.getController();
            bc.setPrimaryStage(stage, gf);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
