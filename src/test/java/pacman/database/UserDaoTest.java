package pacman.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class UserDaoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private UserDao dao;
    private PreparedStatement statement;

    private User user;

    /**
     * Setting up the environment for testing db context.
     * @throws SQLException in case prepared statements are wrong.
     */
    @BeforeEach
    public void setup() throws SQLException { // NOPMD spelled correctly
        ResultSet result = mock(ResultSet.class); // NOPMD mock does not need to be closed
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getInt("Id")).thenReturn(0);
        when(result.getString("Username")).thenReturn("TestUser");
        when(result.getString("Password")).thenReturn("TestPass");
        when(result.getInt("Score")).thenReturn(10);
        statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(result);
        Connection conn = mock(Connection.class); // NOPMD mock does not need to be closed
        when(conn.prepareStatement(anyString())).thenReturn(statement);
        DbConnect dbConn = mock(DbConnect.class);
        when(dbConn.getMyConnection()).thenReturn(conn);
        dao = new UserDao(dbConn);

        user = new User();
        user.setId(0);
        user.setUsername("TestUser");
        user.setPassword("TestPass");
        user.setScore(10);
    }

    @Test
    public void testGetUsername() {
        String username = dao.getUsernameFromDatabase(user);
        assertEquals(user.getUsername(), username);
    }

    @Test
    public void testGetPassword() {
        String password = dao.getUserPasswordFromDatabase(user);
        assertEquals(user.getPassword(), password);
    }

    @Test
    public void testGetScore() {
        int score = dao.retrieveScore(user);
        assertEquals(user.getScore(), score);
    }

    @Test
    public void testGetId() {
        int id = dao.getUserIdFromDatabase(user);
        assertEquals(user.getId(), id);
    }

}
