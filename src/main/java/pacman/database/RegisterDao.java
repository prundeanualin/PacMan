package pacman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class RegisterDao {

    private DbConnect dbConnect;

    public RegisterDao() {
        this(new DbConnect());
    }

    public RegisterDao(DbConnect dbConnect) {
        this.dbConnect = dbConnect;
    }

    /**
     * Method that checks if user exists.
     *
     * @param user as param.
     */
    @SuppressWarnings("PMD")
    public boolean checkUserAlreadyExists(User user) {
        Connection conn = dbConnect.getMyConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        String query = "SELECT Username FROM Users WHERE Username=?";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getUsername());

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
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
        Connection conn = dbConnect.getMyConnection();
        PreparedStatement statement;
        String query = "INSERT INTO Users(Username,Password,Score)" + " VALUES(?,?,?)";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getScore());
            statement.executeUpdate();
            /*
            if (statement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New User Added");
            }
            */
            statement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error occurred in adding user");
        }
    }
}
