package pacman.database;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class RegisterDaoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private RegisterDao registerDao;

    private DbConnect dbConnect;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private User user;

    /**
     * Setting up the testing environment for the database.
     * @throws SQLException in case the connection is not ok
     */
    @BeforeEach
    public void setUp() throws SQLException {
        dbConnect = mock(DbConnect.class);
        connection = mock(Connection.class);
        resultSet = mock(ResultSet.class);
        preparedStatement = mock(PreparedStatement.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(dbConnect.getMyConnection()).thenReturn(connection);

        user = new User();
        user.setUsername("Spongebob");
        user.setPassword("12345"); //NOPMD it's for testing purpose only
        user.setScore(10);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn(user.getUsername());
        when(resultSet.getString(2)).thenReturn(user.getPassword());
        when(resultSet.getInt(3)).thenReturn(user.getScore());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        registerDao = new RegisterDao(dbConnect);
    }

    @Test
    public void testUserExists() {
        assertTrue(registerDao.checkUserAlreadyExists(user));
    }

}
