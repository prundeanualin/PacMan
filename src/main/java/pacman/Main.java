package pacman;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pacman.logic.Direction;
import pacman.logic.GameController;
import pacman.logic.entity.PacMan;

public class Main extends Application {

    public static final int width = 800;
    public static final int height = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // Variable definitions necessary
    public void start(Stage stage) throws IOException {
//        GameController.getInstance().start();
//        VBox root = new VBox();
//        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//        stage.setHeight(850);
//        root.getChildren().add(GameController.getInstance().getScoreLabel());
//        root.getChildren().add(GameController.getInstance().getCanvas());
        Parent root = FXMLLoader.load(getClass().getResource("/views/loginWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        scene.setOnKeyPressed(e -> {
//            PacMan pm = GameController.getInstance().getGame().getLevel().getPacMan();
//            switch (e.getCode()) {
//                case UP:
//                    pm.setNextDirection(Direction.UP);
//                    break;
//                case DOWN:
//                    pm.setNextDirection(Direction.DOWN);
//                    break;
//                case LEFT:
//                    pm.setNextDirection(Direction.LEFT);
//                    break;
//                case RIGHT:
//                    pm.setNextDirection(Direction.RIGHT);
//                    break;
//                default:
//                    // NOOP
//            }
//        });
    }
}
