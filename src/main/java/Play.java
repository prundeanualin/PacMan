import board.BoardFactory;
import ghosts.GhostFactory;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import level.LevelFactory;
import player.PlayerFactory;

public class Play extends Application {

    static GameFactory gf;

    /**.
     * Main starting point of our application
     * @param args args for main
     */
    public static void main(String[] args) {

        PlayerFactory playerFactory = new PlayerFactory();
        GhostFactory ghf = new GhostFactory();
        BoardFactory bf = new BoardFactory();
        LevelFactory levelFactory = new LevelFactory(bf, ghf);

        gf = new GameFactory(playerFactory, levelFactory);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/MainMenu.fxml"));
        Parent mainP = loader.load();

        Scene ourScene = new Scene(mainP);
        stage.setTitle("Splash screen");
        stage.initStyle(StageStyle.DECORATED);
        stage.setWidth(700);
        stage.setHeight(700);
        stage.setScene(ourScene);
        MainMenuController controller = (MainMenuController) loader.getController();
        controller.setGameFactory(gf, stage);

        stage.show();
    }
}
