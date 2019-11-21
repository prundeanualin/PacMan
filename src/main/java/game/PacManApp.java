package game;

import controllers.MainMenuController;
import ghosts.GhostFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import level.LevelFactory;
import player.AccountFactory;

public class PacManApp extends Application {

    static GameFactory gf;

    /**.
     * Main starting point of our application
     * @param args args for main
     */
    public static void main(String[] args) {

        AccountFactory playerFactory = new AccountFactory();
        GhostFactory ghf = new GhostFactory();
        LevelFactory levelFactory = new LevelFactory(ghf);

        gf = new GameFactory(playerFactory, levelFactory);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainMenu.fxml"));
        Parent mainP = (Parent) loader.load();
        Scene ourScene = new Scene(mainP);
        stage.setTitle("Splash screen");
        stage.initStyle(StageStyle.DECORATED);
        stage.setWidth(700);
        stage.setHeight(700);
        stage.setScene(ourScene);
        MainMenuController controller = (MainMenuController) loader.getController();
        controller.setStage(stage);
        controller.setGameFactory(gf);

        controller.getStage().show();
    }
}
