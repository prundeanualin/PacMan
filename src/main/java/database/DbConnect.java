package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;

public class DbConnect {

    /**
     * Method that returns a new connection.
     * @return connection variable
     */
    @SuppressWarnings("PMD")
    public static Connection getMyConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://projects-db.ewi.tudelft.nl:3306/"
                +
                "projects_PacManSEMGroup25?useUnicode=true&"
                +
                "characterEncoding=utf8&useSSL=false&"
                +
                "useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "pu_e7PGKwv6Np3on";
        String pass = "VC1g4yW5b7iy";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Successfully connected");
            connection.close();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        return connection;
    }

}