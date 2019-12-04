package pacman.graphics;

import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import pacman.logic.game.Game;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class GameView extends VBox {

    private StatsCanvas statsCanvas;
    private BoardCanvas boardCanvas;

    public GameView(@NotNull Game game, int width, int height) {
        this.statsCanvas = new StatsCanvas(game, width);
        this.boardCanvas = new BoardCanvas(game.getLevel().getBoard(), width, height);

        getChildren().add(statsCanvas);
        getChildren().add(boardCanvas);
    }

    public StatsCanvas getStatsCanvas() {
        return statsCanvas;
    }

    public BoardCanvas getBoardCanvas() {
        return boardCanvas;
    }
}
