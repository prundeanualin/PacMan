import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;

import javafx.scene.paint.Color;

import javafx.stage.Stage;

import level.MapParser;



public class BoardController {

    private transient Stage primaryStage;
    private transient GameFactory gf;

    @FXML
    private transient Canvas myCanvas;
    private transient GraphicsContext gc;

    /**.
     * Initializing the fxml window connected to this controller with the desired parameters
     */
    public void initialize() {

        myCanvas.setHeight(700);
        myCanvas.setWidth(700);
        gc = myCanvas.getGraphicsContext2D();
        Image background = new Image("static/black-background.jpg");
        gc.drawImage(background, 0, 0);
        paint(1);
    }

    /**.
     * Using the mapParser for creating the board and its walls and pellets
     * @param lvl the current level for the difficulty of the board
     */
    @SuppressWarnings("PMD")
    public void paint(int lvl) {
        MapParser mapParser = new MapParser();
        String mapName = "/Level" + lvl + ".txt";
        char wall = '#';
        char ground = '.';
        char pellet = '*';
        try {
            char[][] map = mapParser.parseMap(mapName);
            double posX = myCanvas.getWidth() / map[0].length;
            double posY = myCanvas.getHeight() / map.length;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {

                    if (map[i][j] == wall) {
                        gc.setFill(Color.BLUE);
                        gc.fillRect(posX * j, posY * i, posX, posY);
                    } else if (map[i][j] == ground) {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(posX * j, posY * i, posX, posY);
                    } else if (map[i][j] == pellet) {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(posX * j, posY * i, posX, posY);
                        gc.setFill(Color.YELLOW);
                        gc.fillRect(posX * j + posX / 2 - 5, posY * i + posY / 2 - 5, 10, 10);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage stage, GameFactory gameFactory) {
        primaryStage = stage;
        gf = gameFactory;
    }
}
