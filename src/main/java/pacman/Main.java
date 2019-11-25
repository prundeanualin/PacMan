package pacman;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pacman.graphics.BoardCanvas;
import pacman.logic.GameController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        GameController.getInstance().start();

        BoardCanvas canvas = new BoardCanvas(GameController.getInstance().getGame().getLevel()
                .getBoard(), 800, 800);
        //Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        VBox root = new VBox();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
