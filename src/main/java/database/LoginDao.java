package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;


/**
 * New login class.
 */

public class LoginDao {
    /**
     * Method to handle login.
     *
     * @param user which is used for login.
     * @return response if true or false.
     */
    @SuppressWarnings("PMD")
    public boolean attemptLogin(User user) {

        boolean status = false;
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT * FROM 'Users' WHERE 'Username'=? AND 'Password'=?";
        try {
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            ((PreparedStatement) statement).setString(2, user.getPassword());
            resultSet = ((PreparedStatement) statement).executeQuery();
            statement.close();
            conn.close();
            if (resultSet.next()) {
                status = true;
            }
            resultSet.close();

        } catch (Exception e) {
            System.out.println("Error");
        }
        //this can be deleted later if a specific window is implemented
        //make use of either the string or the option pane
        JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
        return status;

    }

}