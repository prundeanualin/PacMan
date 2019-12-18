package pacman.database;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDao {

    public @NotNull List<User> getTop(int amount) {
        try (Connection conn = DbConnect.getMyConnection()) {
            PreparedStatement statement = DbConnect.getMyConnection()
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
