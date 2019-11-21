package game;

import java.io.IOException;

import javafx.stage.Stage;
import level.LevelFactory;
import player.AccountFactory;


public class GameFactory {

    private transient AccountFactory playerFactory;
    private transient LevelFactory levelFactory;

    public GameFactory(AccountFactory playerf, LevelFactory levelf) {
        playerFactory = playerf;
        levelFactory = levelf;
    }

    /**.
     * Interface for creating the initial game, making testing more approachable
     */
    public void createGame(Stage stage) throws IOException {
        Game game = new Game(playerFactory.createPlayer(), levelFactory.makeLevel(stage));
        game.setController();
        game.getLevel().showStartText();
    }

    public void setUserName(String username) {
        playerFactory.setUsername(username);
    }

}
