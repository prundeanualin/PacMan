package game;

import board.Board;

import controllers.GameController;
import ghosts.Ghost;
import ghosts.GhostFactory;

import java.io.IOException;

import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import level.Level;
import player.Player;


public class Game {

    private Player player;
    private Level level;
    private GameController gameController;
    private GhostFactory ghostFactory;
    private GameFactory gameFactory;

    private int lvlCount;
    private boolean inProgress;
    private boolean won;

    /**.
     * Creating the game with the initial level and the specific logged player
     * @param play current player from db
     * @param lvl current level of the game
     */
    public Game(Player play, int lvl) {
        player = play;
        lvlCount = lvl;
        ghostFactory = new GhostFactory();
        level = new Level(new Board(lvlCount, PacManApp.WIDTH, PacManApp.HEIGHT),
                ghostFactory.createGhosts(lvlCount));
        inProgress = false;
        won = false;
    }

    /**.
     * Method called from the gameFactory class, meant to create the board display with all
     * its sprites and display a get-ready text for player, following
     * the start of the actual game once the player clicked anywhere on the screen.
     * @param stage the original stage
     * @throws IOException in case fxml file cannot be opened
     */
    public void showGame(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/views/Game.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        this.gameController = (GameController) loader.getController();
        gameController.setStage(stage);
        gameController.setGame(this);
        gameController.startWindow(lvlCount, level.getBoard().getMap());
        stage.show();
    }

    /**.
     * Once the player wins current lvl, it's time to increase it
     */
    public void increaseLevel() {
        lvlCount = lvlCount + 1;
        level = new Level(new Board(lvlCount, PacManApp.WIDTH, PacManApp.HEIGHT),
                ghostFactory.createGhosts(lvlCount));
    }

    public void startGame() {
        inProgress = true;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean getWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getLvlCount() {
        return lvlCount;
    }

    public void setLvlCount(int lvlCount) {
        this.lvlCount = lvlCount;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public void setGameFactory(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GhostFactory getGhostFactory() {
        return ghostFactory;
    }

    public void setGhostFactory(GhostFactory ghostFactory) {
        this.ghostFactory = ghostFactory;
    }
}
