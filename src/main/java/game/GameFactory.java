package game;

import controllers.MainMenuController;

import java.io.IOException;

import player.Account;


public class GameFactory {

    private Account playerAccount;
    private MainMenuController mainMenuController;

    public GameFactory(Account playerac) {
        playerAccount = playerac;
    }

    /**.
     * Interface for creating the initial game, making testing more approachable
     */
    public void createGame(int lvlCount) throws IOException {
        Game game = new Game(playerAccount.createPlayer(), lvlCount);
        game.setGameFactory(this);
        game.showGame(mainMenuController.getStage());
    }

    public void setUserName(String username) {
        playerAccount.setUsername(username);
    }

    public Account getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(Account playerAccount) {
        this.playerAccount = playerAccount;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }
}
