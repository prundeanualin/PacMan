package game;

import controllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import player.Account;

public class PacManApp extends Application {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    static GameFactory gf;

    /**.
     * Main starting point of our application
     * @param args args for main
     */
    public static void main(String[] args) {

        Account user = new Account();

        gf = new GameFactory(user);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainMenu.fxml"));
        Parent mainP = (Parent) loader.load();
        Scene ourScene = new Scene(mainP);
        stage.setTitle("Splash screen");
        stage.initStyle(StageStyle.DECORATED);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setScene(ourScene);
        MainMenuController controller = (MainMenuController) loader.getController();
        controller.setStage(stage);
        controller.setGameFactory(gf);

        controller.getStage().show();
    }
}
