package pacman.database;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDao {

    public void enterScore(User user, int score) {
        try (Connection conn = new DbConnect().getMyConnection()) {
            PreparedStatement statement = conn
                    .prepareStatement("UPDATE Users SET Score=? WHERE Username=?");
            statement.setInt(1, score);
            statement.setString(2, user.getUsername());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public @NotNull List<User> getTop(int amount) {
        try (Connection conn = new DbConnect().getMyConnection()) {
            PreparedStatement statement = conn
                    .prepareStatement("SELECT * FROM Users ORDER BY Score DESC LIMIT ?");
            statement.setInt(1, amount);

            List<User> result = new ArrayList<>();
            ResultSet results = statement.executeQuery(); // NOPMD Everything is closed
            while (results.next()) {
                User user = new User();
                user.setId(results.getInt("Id"));
                user.setUsername(results.getString("Username"));
                user.setPassword(results.getString("Password"));
                user.setScore(results.getInt("Score"));
                result.add(user);
            }
            statement.close();
            results.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
