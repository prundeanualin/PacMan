package database;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pacman.database.DbConnect;
import pacman.database.RegisterDao;
import pacman.database.User;
import pacman.database.UserDao;

@SuppressWarnings("PMD")
public class RegisterDaoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private UserDao userDao;
    private RegisterDao registerDao;


    private DbConnect dbConnect;


    private Connection connection;


    private PreparedStatement preparedStatement;


    private ResultSet resultSet;


    private User user;

    /**
     * setting up the testing environment.
     * @throws SQLException sql wrongly prepared statement.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        dbConnect = mock(DbConnect.class);
        connection = mock(Connection.class);
        resultSet = mock(ResultSet.class);
        preparedStatement = mock(PreparedStatement.class);
        ResultSet result = mock(ResultSet.class); // NOPMD mock does not need to be closed
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getInt("Id")).thenReturn(0);
        when(result.getString("Username")).thenReturn("SpongeBob");
        when(result.getString("Password")).thenReturn("12345");
        when(result.getInt("Score")).thenReturn(10);
        preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(result);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(dbConnect.getMyConnection()).thenReturn(connection);
        registerDao = new RegisterDao(dbConnect);

        user = new User();
        user.setId(0);
        user.setUsername("SpongeBob");
        user.setPassword("12345");
        user.setScore(10);

        registerDao = new RegisterDao(dbConnect);

    }




//    @Test
//    public void testUserExists(){
//        boolean res = registerDao.checkUserAlreadyExists(user);
//        assertTrue(res);
//    }
//
//
//    @Test
//    public void testDuplicateUser(){
//        user = new User();
//        user.setUsername("Spongebob");
//        user.setPassword("12345");
//        user.setScore(10);
//        assertTrue(registerDao.checkUserAlreadyExists(user));
//    }


//    @Test
//    public void testAddingUser() {
//        registerDao.addUser(user);
//        Mockito.verify(registerDao, times(1)).addUser(user);
//    }

    @Test
    public void addNewUser() {
        User user2 = new User();
        userDao = new UserDao();
        user2.setUsername("Lil Peep");
        user2.setPassword("12345");
        user2.setScore(10);
        User userCopy = user2;
        RegisterDao registerDao = new RegisterDao();
        registerDao.addUser(user2);
        assertEquals(userCopy.getUsername(), userDao.getUsernameFromDatabase(user2));
        userDao.deleteUser(user2);
    }

    @Test
    public void checkUserExistTrue() {
        user = new User();
        userDao = new UserDao();
        user.setUsername("Lil Peep");
        user.setPassword("12345");
        user.setScore(10);
        RegisterDao registerDao = new RegisterDao();
        registerDao.addUser(user);
        assertEquals(true, registerDao.checkUserAlreadyExists(user));
        userDao.deleteUser(user);
    }

    @Test
    public void checkUserExistsFalse() {
        user = new User();
        userDao = new UserDao();
        user.setUsername("Lil Peep");
        user.setPassword("12345");
        user.setScore(10);
        RegisterDao registerDao = new RegisterDao();
        assertEquals(false, registerDao.checkUserAlreadyExists(user));
    }

    @Test
    public void testAddingDuplicates() {
        User user2 = user;
        RegisterDao registerDao = Mockito.mock(RegisterDao.class);
        registerDao.addUser(user);
        Mockito.when(registerDao.checkUserAlreadyExists(user2)).thenReturn(true);
    }

}
