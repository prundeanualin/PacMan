package database;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.swing.JOptionPane;


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
        DbConnect dbConnect = new DbConnect();
        boolean status = false;
        Connection conn = dbConnect.getMyConnection();
        PreparedStatement statement;
        EncryptionDao encryptionDao = new EncryptionDao();
        ResultSet resultSet;
        byte[] encryptedPass = new byte[64];
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        try {
            String newSalt = encryptionDao.getUserSalt(user);
            if (newSalt.isEmpty()) {
                return false;
            }
            encryptedPass = passwordEncryptionService.getEncryptedPassword(user.getPassword(),
                    Base64.getDecoder().decode(newSalt));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("error occurred");
        }
        String query = "SELECT Username,Password FROM Users WHERE Username=? AND Password=?";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, Base64.getEncoder().encodeToString(encryptedPass));
            resultSet = statement.executeQuery();

            if (resultSet.next() == false) {
                status = false;
            } else {
                status = true;
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error in logging in");
        }
        //this can be deleted later if a specific window is implemented
        //make use of either the string or the option pane
        System.out.println(status);
        return status;

    }

}
