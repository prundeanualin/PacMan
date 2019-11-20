package player;

public class PlayerFactory {

    private Player player;

    public Player createPlayer(String username, String password) {
        player = new Player(username, password);
        return player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
