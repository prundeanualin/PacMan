package database;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

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
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        byte[] encryptedPass = new byte[160];
        byte[] userSalt = new byte[8];
        try{
            //userSalt = passwordEncryptionService.getSalt();
            userSalt = passwordEncryptionService.getSalt();
            System.out.println("Salt add" + userSalt);
            System.out.println("Salt string" + Base64.getEncoder().encodeToString(userSalt));
            encryptedPass = passwordEncryptionService.getEncryptedPassword(user.getPassword(), userSalt);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        { }
        String query = "INSERT INTO Users(Username,Password,PassSalt,Score)" + " VALUES(?,?,?,?)";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, Base64.getEncoder().encodeToString(encryptedPass));
            statement.setString(3, Base64.getEncoder().encodeToString(userSalt));
            statement.setInt(4, user.getScore());
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
