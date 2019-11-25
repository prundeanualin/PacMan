package level;

import board.Board;
import controllers.LevelController;
import ghosts.GhostFactory;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LevelFactory {

    int lvlCount;
    private Level level;
    private transient GhostFactory gf;

    /**.
     * Creating a factory for generating a level, more like an intermediate
     * testable class for separating the initial setup from creating a new level
     * @param sp ghostfactory for creating the ghosts and their AI
     */
    public LevelFactory(GhostFactory sp) {
        lvlCount = 1;
        gf = sp;
    }

    public Level getLevel() {
        return level;
    }

    public void increaseLevel(Stage stage) throws IOException {
        lvlCount = lvlCount + 1;
        level = makeLevel(stage);
    }

    public int getLvlCount() {
        return lvlCount;
    }

    /**.
     * This function takes the current stage launched by the application
     * and creates a new game scene,
     * with board, pellet, ghosts and player displayed.
     * @param stage original stage launched by the application
     * @return the current level created
     * @throws IOException in case mapParser fails
     */
    public Level makeLevel(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/views/Level.fxml"));
        Parent root = loader.load();
        LevelController levelController = (LevelController) loader.getController();
        levelController.setStage(stage);
        levelController.startWindow(lvlCount);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        level = new Level(lvlCount, createBoard(lvlCount),
                gf.createGhosts(lvlCount), levelController);
        return level;
    }

    public Board createBoard(int lvl) {
        Board bd = new Board(lvl);
        return bd;
    }

    public void setLvlCount(int lvlCount) {
        this.lvlCount = lvlCount;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

}
