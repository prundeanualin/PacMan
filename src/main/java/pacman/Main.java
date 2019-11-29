package pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pacman.logic.GameController;

import java.io.IOException;

public class Main extends Application {

    public static final int width = 800;
    public static final int height = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // Variable definitions necessary
    public void start(Stage stage) throws IOException {

//        Parent root = FXMLLoader.load(getClass().getResource("/views/splashScreen.fxml"));
        //        Parent root = FXMLLoader.load(getClass().getResource("/views/gameWindow.fxml"));
        //        GameController.getInstance().start();
        GameController.getInstance().start();
        VBox root = new VBox();
//        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(GameController.getInstance().getScoreLabel());
        root.getChildren().add(GameController.getInstance().getCanvas());
        Scene scene = new Scene(root);
//        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setHeight(height + 50);
        stage.setWidth(width);
        stage.setScene(scene);
        stage.show();

//        Scene scene = new Scene(root);

//        stage.setScene(scene);
//        stage.show();

    }
}
