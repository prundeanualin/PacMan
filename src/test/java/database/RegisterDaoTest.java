package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


public class RegisterDaoTest {

    private RegisterDao registerDao;
    private UserDao userDao;


    @Mock
    private ResultSet resultSet;

    @Mock
    private PreparedStatement preparedStatement;

    private User user;

    @Before
    public void setUp()
    {
        userDao = new UserDao();
        registerDao =  new RegisterDao();
        user.setUsername("SpongeBob");
        user.setPassword("12345");
        user.setScore(10);

    }

    @Test
    public void testR() {
        User savedUser = user;
        registerDao.addUser(user);
        assertEquals(savedUser, user);
    }

    @After
    public void End()
    {
        userDao.deleteUser(user);
    }
}
