package pacman;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public static final int width = 800;
    public static final int height = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // Variable definitions necessary
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/views/splashScreen.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
}
