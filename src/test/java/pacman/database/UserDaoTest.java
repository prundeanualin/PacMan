package database;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pacman.database.*;

@SuppressWarnings("PMD")
public class UserDaoTest {

    private UserDao userDao;
    private RegisterDao registerDao;
    private User user;

    /**
     * setting up the testing environment.
     */
    @BeforeEach
    public void setUp() {
        registerDao = Mockito.mock(RegisterDao.class);
        user = new User();
        user.setUsername("Dj Mustard");
        user.setPassword("12345");
        user.setScore(10);
        registerDao = new RegisterDao();
        registerDao.addUser(user);

    }

    @Test
    public void testGetUsernameFromDatabaseMockito() {
        userDao = Mockito.mock(UserDao.class);
        Mockito.when(userDao.getUsernameFromDatabase(user)).thenReturn("Dj Mustard");
    }

    @Test
    public void testGetUsernameFromDatabase() {
        UserDao userDao1 = new UserDao();
        User user2 = user;
        assertEquals(user2.getUsername(), userDao1.getUsernameFromDatabase(user));
    }


    @Test
    public void testGetScoreFromDatabase() {
        UserDao userDao1 = new UserDao();
        User user2 = user;
        assertEquals(user2.getScore(), userDao1.retrieveScore(user));
    }

    @Test
    public void testGetId() {
        UserDao userDao1 = new UserDao();
        int id = userDao1.getUserIdFromDatabase(user);
        System.out.println(id);
        assertEquals(id, userDao1.getUserIdFromDatabase(user));
    }

    @Test
    public void testUpdateUsername() throws NoSuchAlgorithmException {
        userDao = new UserDao();
        EncryptionDao encryptionDao = new EncryptionDao();
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        System.out.println(encryptionDao.getUserSalt(user));
        System.out.println("original method " + passwordEncryptionService.getSalt());
        int id = userDao.getUserIdFromDatabase(user);
        user.setId(id);
        System.out.println("Id is this  " + id);
        user.setUsername("A boogie wit da Hoodie");
        userDao.updateUserUsername(user);
        String result = userDao.getUsernameFromDatabase(user);
        assertEquals("A boogie wit da Hoodie", result);
    }

    @Test
    public void testUpdateScore() {
        UserDao userDao1 = new UserDao();
        user.setScore(101);
        userDao1.updateUserScore(user);
        assertEquals(101, userDao1.retrieveScore(user));
    }

    @Test
    public void testUpdatePassword() throws InvalidKeySpecException,
            NoSuchAlgorithmException {
        userDao = new UserDao();
        user.setPassword("newPass");
        userDao.updateUserPassword(user);
        EncryptionDao encryptionDao = new EncryptionDao();
        String userSalt = encryptionDao.getUserSalt(user);
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        byte[] encPass = passwordEncryptionService
                .getEncryptedPassword(user.getPassword(), Base64.getDecoder().decode(userSalt));
        assertEquals(userDao.getUserPasswordFromDatabase(user),
                Base64.getEncoder().encodeToString(encPass));
    }

    @Test
    public void testCrypto() throws InvalidKeySpecException, NoSuchAlgorithmException {
        EncryptionDao encryptionDao = new EncryptionDao();
        userDao = new UserDao();
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        String userSalt = encryptionDao.getUserSalt(user);
        byte[] encryptedPass = passwordEncryptionService
                .getEncryptedPassword(user.getPassword(), userSalt.getBytes());
        boolean status;
        status = passwordEncryptionService
                .securityCheck(user.getPassword(), encryptedPass, userSalt.getBytes());
        assertTrue(status);
    }

    @Test
    public void testGetPass() throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user2 = new User();
        user2.setUsername("abcd");
        user2.setPassword("pass123");
        RegisterDao registerDao = new RegisterDao();
        registerDao.addUser(user2);
        userDao = new UserDao();
        EncryptionDao encryptionDao = new EncryptionDao();
        String salt = encryptionDao.getUserSalt(user2);
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        byte[] encPass = passwordEncryptionService
                .getEncryptedPassword(user2.getPassword(), Base64.getDecoder().decode(salt));
        String passFromDb = userDao.getUserPasswordFromDatabase(user2);
        userDao.deleteUser(user2);
        assertEquals(Base64.getEncoder().encodeToString(encPass), passFromDb);
    }

    @Test
    public void testDeleteUser() {
        User user2 = new User();
        user2.setPassword("123f");
        user2.setUsername("Drake");
        user2.setScore(129);
        registerDao = new RegisterDao();
        registerDao.addUser(user2);
        UserDao userDao1 = new UserDao();
        userDao1.deleteUser(user2);
        assertEquals("No user found", userDao1.getUsernameFromDatabase(user2));
    }

    @AfterEach
    public void end() {
        UserDao userDao = new UserDao();
        userDao.deleteUser(user);
    }
}
