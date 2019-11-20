import level.Level;
import level.LevelFactory;
import player.Player;
import player.PlayerFactory;

public class Game {

    private Player player;
    private Level level;

    private boolean inProgress;
    private boolean won;

    /**.
     * Creating the game with the initial level and the specific logged player
     * @param play current player from db
     * @param lvl current level of the game
     */
    public Game(Player play, Level lvl) {
        player = play;
        level = lvl;
        inProgress = false;
        won = false;
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

}
