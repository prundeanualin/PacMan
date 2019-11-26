package pacman;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pacman.graphics.BoardCanvas;
import pacman.logic.Direction;
import pacman.logic.GameController;
import pacman.logic.entity.PacMan;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // Variable definitions necessary
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

        scene.setOnKeyPressed(e -> {
            PacMan pm = GameController.getInstance().getGame().getLevel().getPacMan();
            switch (e.getCode()) {
                case UP:
                    pm.setNextDirection(Direction.UP);
                    break;
                case DOWN:
                    pm.setNextDirection(Direction.DOWN);
                    break;
                case LEFT:
                    pm.setNextDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    pm.setNextDirection(Direction.RIGHT);
                    break;
                default:
                    // NOOP
            }
        });
    }
}