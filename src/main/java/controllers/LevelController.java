package controllers;

import game.Game;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;

import level.MapParser;



public class LevelController {

    private static final double  HEIGHT = 700;
    private static final double  WIDTH = 700;
    private boolean start;
    private Game game;
    private Stage stage;
    @FXML
    public transient Label text;
    @FXML
    private transient Canvas myCanvas;
    private transient GraphicsContext gc;

    /**.
     * Initializing the fxml window connected to this controller with the desired parameters
     */
    public void initialize() {

        myCanvas.setHeight(HEIGHT);
        myCanvas.setWidth(WIDTH);
        gc = myCanvas.getGraphicsContext2D();
        Image background = new Image("static/black-background.jpg");
        gc.drawImage(background, 0, 0);
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

    public void startWindow(int lvlCount) throws IOException {
        paint(lvlCount);
    }

    /**.
     * Player is prompted with a preparing message when starting the game, following a click
     * on the screen and the start of the game.
     */
    public void showStartMessage() {
        text.setText("Click to start the game");
        text.toFront();
        text.setFont(Font.font(30));
        text.setTextFill(Color.WHITE);
        text.setTranslateX(HEIGHT / 5);
        text.setTranslateY(HEIGHT / 5);
    }

    /**.
     * Player clicked on the screen, it's time to play !
     * Will be implemented further with ActionListeners to move PacMan with keyboard
     */
    public void startGame() {
        text.setText("It worked perfectly");
        text.toFront();
        text.setFont(Font.font(30));
        text.setTextFill(Color.RED);
        text.setTranslateX(HEIGHT / 5);
        text.setTranslateY(HEIGHT / 5);
        game.startGame();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
