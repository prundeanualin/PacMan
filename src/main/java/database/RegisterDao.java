package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class RegisterDao {
    /**
     * Method that checks if user exists.
     *
     * @param user as param.
     */
    @SuppressWarnings("PMD")
    public boolean checkUserAlreadyExists(User user) {
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT * FROM 'Users' WHERE 'Username' =?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());

            resultSet = ((PreparedStatement) statement).executeQuery();

            if (resultSet.next()) {
                statement.close();
                resultSet.close();
                return true;
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return false;
    }

    /**
     * Method that adds a new user.
     *
     * @param user as param.
     */
    @SuppressWarnings("PMD")
    public void addUser(User user) {
        user.setScore(0);
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        String query = "INSERT INTO 'Users'('Username','Password','Score')VALUES(?,?,?)";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            ((PreparedStatement) statement).setString(2, user.getPassword());
            ((PreparedStatement) statement).setInt(3, user.getScore());

            if (((PreparedStatement) statement).executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New User Added");
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error occurred in adding user");
        }
    }
}
