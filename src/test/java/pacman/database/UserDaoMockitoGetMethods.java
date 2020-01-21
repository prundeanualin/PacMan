package pacman.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@SuppressWarnings("PMD")// Class is not a bean.
public class UserDaoMockitoGetMethods {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private UserDao userDao;
    private PreparedStatement statement;
    private PasswordEncryptionService passwordEncryptionService;
    private User user;
    private String salt;

    /**
     * Set up the environment for the db context under testing.
     *
     * @throws SQLException in case prepared statements are wrong.
     */
    @BeforeEach
    public void setup() throws SQLException, NoSuchAlgorithmException {
        salt = Base64.getEncoder().encodeToString(passwordEncryptionService.getSalt());
        ResultSet result = mock(ResultSet.class);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getInt("Id")).thenReturn(0);
        when(result.getString("Username")).thenReturn("Dj Mustard");
        when(result.getString("Password")).thenReturn("12345");
        when(result.getString("PassSalt")).thenReturn(salt);
        when(result.getInt("Score")).thenReturn(10);
        statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(result);
        Connection conn = mock(Connection.class); // NOPMD mock does not need to be closed
        when(conn.prepareStatement(anyString())).thenReturn(statement);
        DbConnect dbConn = mock(DbConnect.class);
        when(dbConn.getMyConnection()).thenReturn(conn);
        userDao = new UserDao(dbConn);

        user = new User();
        user.setId(0);
        user.setUsername("Dj Mustard");
        user.setPassword("12345");
        user.setScore(10);
    }

    @Test
    public void testGetUsername() {
        String username = userDao.getUsernameFromDatabase(user);
        assertEquals(user.getUsername(), username);
    }

    @Test
    public void testGetPassword() {
        String password = userDao.getUserPasswordFromDatabase(user);
        assertEquals(user.getPassword(), password);
    }

    @Test
    public void testGetScore() {
        int score = userDao.retrieveScore(user);
        assertEquals(user.getScore(), score);
    }

    @Test
    public void testGetId() {
        int id = userDao.getUserIdFromDatabase(user);
        assertEquals(user.getId(), id);
    }
}