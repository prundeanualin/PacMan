package database;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.sql.*;

public class LoginDao {
    public boolean AttemptLogin(User user)
    {

        Connection conn = DbConnect.getMyConnection();
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT * FROM 'Users' WHERE 'Username'=? AND 'Password'=?";
        try{
            statement = conn.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, user.getUsername());
            ((PreparedStatement) statement).setString(2, user.getPassword());
            resultSet = ((PreparedStatement) statement).executeQuery();
            if(resultSet.next())
                result = "Successfully logged in!";
                return true;
        }
        catch(SQLException e){

        }
        //this can be deleted later if a specific window is implemented
        //make use of either the string or the option pane
        JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
        result = "Incorrect credentials!";
        return false;
    }
    @Getter
    @Setter
    private String result;
}
