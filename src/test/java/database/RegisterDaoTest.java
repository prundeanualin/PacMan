//package database;
//
//import static junit.framework.TestCase.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.when;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.junit.jupiter.api.BeforeAll;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//@SuppressWarnings("PMD.BeanMembersShouldSerialize")
//// It's not a bean, but a test with
////some helper test scope variables
//public class RegisterDaoTest {
//
//    private UserDao userDao;
//
//    @Mock
//    private DbConnect dbConnect;
//
//    @Mock
//    private Connection connection;
//
//    @Mock
//    private PreparedStatement preparedStatement;
//
//    @Mock
//    private ResultSet resultSet;
//
//
//    private User user;
//
//    /**
//     * Setting up the testing environment for the database.
//     * @throws SQLException in case the connection is not ok
//     */
//    @BeforeAll
//    public void setUp() throws SQLException {
//        userDao = new UserDao();
//
//        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
//        when(dbConnect.getMyConnection()).thenReturn(connection);
//
//        user = new User();
//        user.setUsername("Spongebob");
//        user.setPassword("12345"); //NOPMD it's for testing purpose only
//        user.setScore(10);
//
//        when(resultSet.first()).thenReturn(true);
//        when(resultSet.getString(1)).thenReturn(user.getUsername());
//        when(resultSet.getString(2)).thenReturn(user.getPassword());
//        when(resultSet.getInt(3)).thenReturn(user.getScore());
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//    }
//
//    @Test
//    public void nullCreateThrowsException() {
//        RegisterDao registerDao = Mockito.mock(RegisterDao.class);
//        Mockito.doThrow(new Exception()).when(registerDao).addUser(null);
//    }
//
//    @Test
//    public void testAddingUser() {
//        RegisterDao registerDao = Mockito.mock(RegisterDao.class);
//        registerDao.addUser(user);
//        Mockito.verify(registerDao,times(1)).addUser(user);
//    }
//
//    @Test
//    public void addNewUser() {
//        User user2 = new User();
//        userDao = new UserDao();
//        user2.setUsername("Lil Peep");
//        user2.setPassword("12345");
//        user2.setScore(10);
//        User userCopy = user2;
//        RegisterDao registerDao = new RegisterDao();
//        registerDao.addUser(user2);
//        assertEquals(userCopy.getUsername(), userDao.getUsernameFromDatabase(user2));
//        userDao.deleteUser(user2);
//    }
//
//    @Test
//    public void checkUserExistTrue() {
//        user = new User();
//        userDao = new UserDao();
//        user.setUsername("Lil Peep");
//        user.setPassword("12345");
//        user.setScore(10);
//        RegisterDao registerDao = new RegisterDao();
//        registerDao.addUser(user);
//        assertEquals(true, registerDao.checkUserAlreadyExists(user));
//        userDao.deleteUser(user);
//    }
//
//    @Test
//    public void checkUserExistsFalse() {
//        user = new User();
//        userDao = new UserDao();
//        user.setUsername("Lil Peep");
//        user.setPassword("12345");
//        user.setScore(10);
//        RegisterDao registerDao = new RegisterDao();
//        assertEquals(false, registerDao.checkUserAlreadyExists(user));
//    }
//
//    @Test
//    public void testAddingDuplicates() {
//        User user2 = user;
//        RegisterDao registerDao = Mockito.mock(RegisterDao.class);
//        registerDao.addUser(user);
//        Mockito.when(registerDao.checkUserAlreadyExists(user2)).thenReturn(true);
//    }
//
//}
