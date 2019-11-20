import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import level.LevelFactory;
import player.PlayerFactory;

public class GameFactory {

    private PlayerFactory playerFactory;
    private LevelFactory levelFactory;

    public GameFactory(PlayerFactory playerf, LevelFactory levelf) {
        playerFactory = playerf;
        levelFactory = levelf;
    }

    /**.
     * Interface for creating the initial game, making testing more approachable
     * @return
     */
    public Game createGame() {

        String usern = "";
        String passw = "";
        return new Game(playerFactory.createPlayer(usern, passw), levelFactory.getLevel());
    }

    public PlayerFactory getPlayerFactory() {
        return playerFactory;
    }

    public void setPlayerFactory(PlayerFactory playerFactory) {
        this.playerFactory = playerFactory;
    }

    public LevelFactory getLevelFactory() {
        return levelFactory;
    }

    public void setLevelFactory(LevelFactory levelFactory) {
        this.levelFactory = levelFactory;
    }
}
