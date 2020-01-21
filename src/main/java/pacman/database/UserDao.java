package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class UserDao {

    private DbConnect connect;

    public UserDao() {
        this(new DbConnect());
    }

    public UserDao(DbConnect connect) {
        this.connect = connect;
    }

    /**
     * Method which retrieves user.
     *
     * @param user as param.
     * @return the desired user.
     */
    public String getUsernameFromDatabase(User user) {
        String finalUsername = ""; // NOPMD variable used
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        ResultSet resultSet; // NOPMD is closed
        String query = "SELECT Username FROM Users WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if (resultSet.next()) {
                finalUsername = resultSet.getString("Username"); // NOPMD variable used
            } else {
                finalUsername = "No user found"; // NOPMD variable used
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            finalUsername = "Error";
        }
        return finalUsername;
    }

    /**
     * Method which retrieves user.
     *
     * @param user as param.
     * @return the desired user password.
     */
    public String getUserPasswordFromDatabase(User user) {
        String finalPassword = ""; // NOPMD variable used
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        ResultSet resultSet; // NOPMD is closed
        String query = "SELECT Password FROM Users WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if (resultSet.next()) {
                finalPassword = resultSet.getString("Password"); // NOPMD variable used
            } else {
                finalPassword = "No user password found"; // NOPMD variable used
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            finalPassword = "Error";
        }
        return finalPassword;
    }

    /**
     * Method which retrieves score.
     *
     * @param user as param.
     * @return user's score.
     */
    public int retrieveScore(User user) {
        int score = 0; // NOPMD variable used
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        ResultSet resultSet; // NOPMD is closed
        String query = "SELECT Score FROM Users WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if (resultSet.next()) {
                score = resultSet.getInt("Score");
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    /**
     * Method which retrieves id.
     *
     * @param user as param.
     * @return user's id.
     */
    public int getUserIdFromDatabase(User user) {
        int id = 0; // NOPMD variable used
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        ResultSet resultSet; // NOPMD is closed
        String query = "SELECT Id FROM Users WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("Id");
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Method which updates user score.
     *
     * @param user as param.
     */
    public void updateUserScore(User user) {
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        String query = "UPDATE Users SET Score=? WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setInt(1, user.getScore());
            ((PreparedStatement) statement).setString(2, user.getUsername());
            ((PreparedStatement) statement).executeUpdate();
            /*
            if (((PreparedStatement) statement).executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Successfully updated", "Database info", 1);
            }
             */
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which updates username.
     *
     * @param user as param.
     */
    public void updateUserUsername(User user) {
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        String query = "UPDATE Users SET Username=? WHERE Password=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            ((PreparedStatement) statement).setString(2, user.getPassword());
            ((PreparedStatement) statement).executeUpdate();
            /*
            if (((PreparedStatement) statement).executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Successfully updated", "Database info", 1);
            }
             */
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which updates password.
     *
     * @param user as param.
     */
    public void updateUserPassword(User user) {
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        String query = "UPDATE Users SET Password=? WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getPassword());
            ((PreparedStatement) statement).setString(2, user.getUsername());
            ((PreparedStatement) statement).executeUpdate();
            /*
            if (((PreparedStatement) statement).executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Successfully updated", "Database info", 1);
            }
             */
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which deletes user.
     *
     * @param user as param.
     */
    public void deleteUser(User user) {
        Connection conn = connect.getMyConnection(); // NOPMD is closed
        Statement statement; // NOPMD is closed
        String query = "DELETE FROM Users WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            ((PreparedStatement) statement).executeUpdate();
            /*
            if (((PreparedStatement) statement).executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Successfully updated", "Database info", 1);
            }
            */
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
