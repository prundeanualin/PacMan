package pacman.graphics.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pacman.database.LeaderboardDao;
import pacman.database.User;
import pacman.graphics.Style;
import pacman.logic.game.GameController;
import pacman.logic.game.GameState;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class LeaderboardController implements Initializable {

    private static int LEADERBOARD_AMOUNT = 5;

    @FXML
    private Label status;

    @FXML
    private VBox scores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (GameController.getInstance().getGame().getState().getValue() == GameState.LOST) {
            status.setText("You Lost :(");
        } else {
            status.setText("You Won!");
        }

        LeaderboardDao dao = new LeaderboardDao();
        dao.enterScore(GameController.getInstance().getUser(),
                GameController.getInstance().getGame().getPlayer().getScore().get());
        List<User> users = dao.getTop(LEADERBOARD_AMOUNT);
        for (int i = 0; i < users.size(); i++) {
            Label label = new Label((i + 1) + ". " + users.get(i).getUsername() + ": "
                    + users.get(i).getScore());
            label.setTextFill(Style.CLASSIC.getTextColour());
            label.setFont(Font.font(24));
            scores.getChildren().add(label);
        }
    }

}
