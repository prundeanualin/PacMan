import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(new TestPane()));
        primaryStage.show();
    }
}
