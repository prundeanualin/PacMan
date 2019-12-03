package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RegisterDao {
    /**
     * Method that checks if user exists.
     *
     * @param user as param.
     */
    @SuppressWarnings("PMD")
    public boolean checkUserAlreadyExists(User user) {
        DbConnect dbConnect = new DbConnect();
        Connection conn = dbConnect.getMyConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        String query = "SELECT Username FROM Users WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getUsername());

            resultSet = statement.executeQuery();

            if (resultSet.next() == false) {
                return false;
            }
            statement.close();
            resultSet.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return true;
    }

    /**
     * Method that adds a new user.
     *
     * @param user as param.
     */
    @SuppressWarnings("PMD")
    public void addUser(User user) {
        DbConnect dbConnect = new DbConnect();
        Connection conn = dbConnect.getMyConnection();
        PreparedStatement statement;
        String query = "INSERT INTO Users(Username,Password,Score)" + " VALUES(?,?,?)";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getScore());

            if (statement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New User Added");
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error occurred in adding user");
        }
    }
}
