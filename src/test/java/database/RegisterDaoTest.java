package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;

import javax.persistence.EntityManager;
import java.sql.Connection;
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
