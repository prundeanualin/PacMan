package database;

import javax.swing.*;
import java.sql.*;

public class UserDao {
    public String getUsernameFromDatabase(User user)
    {
        String finalUsername = "";
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT 'Username' FROM 'Users' WHERE 'Username' =?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if(resultSet.next())
            {
                finalUsername = resultSet.getString("Username");
            }
            else finalUsername = "No user found";
        }
        catch(SQLException e)
        {
            System.out.println("Error occurred");
            finalUsername = "Error";
        }
        return finalUsername;
    }
    public int retrieveScore(User user)
    {
        int score = 0;
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT 'Score' FROM 'Users' WHERE 'Username' =?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if(resultSet.next())
            {
                score = resultSet.getInt("Score");
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error occurred");
        }
        return score;
    }
    public int getUserIdFromDatabase(User user)
    {
        int id = 0;
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT 'Id' FROM 'Users' WHERE 'Username' =?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if(resultSet.next())
            {
                id = resultSet.getInt("Id");
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error occurred");
        }
        return id;
    }
    public void updateUserScore(User user)
    {
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        String query = "UPDATE 'Users' SET 'Score'=?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setInt(1, user.getScore());
            ((PreparedStatement) statement).executeUpdate();
            if(((PreparedStatement) statement).executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "Successfully updated", "Database info", 1);
        }
        catch(SQLException e)
        {
            System.out.println("Error occurred");
        }
    }
    public void updateUserUsername(User user)
    {
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        String query = "UPDATE 'Users' SET 'Username'=?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            ((PreparedStatement) statement).executeUpdate();
            if(((PreparedStatement) statement).executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "Successfully updated", "Database info", 1);
        }
        catch(SQLException e)
        {
            System.out.println("Error occurred");
        }
    }
    public void updateUserPassword(User user)
    {
        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        String query = "UPDATE 'Users' SET 'Password'=?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getPassword());
            ((PreparedStatement) statement).executeUpdate();
            if(((PreparedStatement) statement).executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "Successfully updated", "Database info", 1);
        }
        catch(SQLException e)
        {
            System.out.println("Error occurred");
        }
    }
}
