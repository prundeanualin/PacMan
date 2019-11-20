package database;

import javax.swing.*;
import java.sql.*;

public class RegisterDao {
    public boolean checkUserAlreadyExists(User user)
    {
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT * FROM 'Users' WHERE 'Username' =?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());

            resultSet = ((PreparedStatement) statement).executeQuery();

            if(resultSet.next())
                return true;
        }
        catch (Exception e)
        {
            System.out.println("Error occurred");
        }
        return false;
    }

    public void addUser(User user)
    {
        user.setScore(0);
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        String query = "INSERT INTO 'Users'('Username','Password','Score')VALUES(?,?,?)";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            ((PreparedStatement) statement).setString(2, user.getPassword());
            ((PreparedStatement) statement).setInt(3, user.getScore());

            if(((PreparedStatement) statement).executeUpdate() > 0)
                JOptionPane.showMessageDialog(null,"New User Added");
        }
        catch(SQLException e)
        {
            System.out.println("Error occurred in adding user");
        }
    }
}
