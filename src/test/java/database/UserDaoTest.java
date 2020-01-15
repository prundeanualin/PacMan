package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import static junit.framework.TestCase.*;

@SuppressWarnings("PMD")
public class UserDaoTest {

    private UserDao userDao;
    private LoginDao loginDao;
    private RegisterDao registerDao;
    private User user;

    @BeforeEach
    public void setUp() {
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
        assertEquals(id, userDao1.getUserIdFromDatabase(user));
    }

    @Test
    public void testUpdateUsername() throws NoSuchAlgorithmException {
        UserDao userDao1 = new UserDao();
        EncryptionDao encryptionDao = new EncryptionDao();
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        User user2 = new User();
        user2.setPassword("123f");
        user2.setUsername("Drake");
        user2.setScore(129);
        registerDao = new RegisterDao();
        registerDao.addUser(user2);
        //String salty = Base64.getEncoder().encodeToString(encryptionDao.getUserSalt(user2));
        System.out.println(encryptionDao.getUserSalt(user2).toString());
        System.out.println("original method " + passwordEncryptionService.getSalt());
        user2.setUsername("A boogie wit da Hoodie");
        userDao1.updateUserUsername(user2);
        String result = userDao1.getUsernameFromDatabase(user2);
        userDao1.deleteUser(user2);
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
    public void testUpdatePassword() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        EncryptionDao encryptionDao = new EncryptionDao();
        userDao = new UserDao();
        user.setPassword("newPass");
        userDao.updateUserPassword(user);
        String userSalt = encryptionDao.getUserSalt(user);
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        byte[] encPass = passwordEncryptionService.getEncryptedPassword(user.getPassword(), Base64.getDecoder().decode(userSalt));
        assertEquals(userDao.getUserPasswordFromDatabase(user), Base64.getEncoder().encodeToString(encPass));
    }

    @Test
    public void testCrypto() throws InvalidKeySpecException, NoSuchAlgorithmException {
        EncryptionDao encryptionDao = new EncryptionDao();
        userDao = new UserDao();
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        String userSalt = encryptionDao.getUserSalt(user);
        byte[] encryptedPass = passwordEncryptionService.getEncryptedPassword(user.getPassword(), userSalt.getBytes());
        boolean status;
        if (passwordEncryptionService.securityCheck(user.getPassword(), encryptedPass, userSalt.getBytes()))
            status = true;
        else
            status = false;
        assertTrue(status);
    }

    @Test
    public void testGetPass() throws InvalidKeySpecException, NoSuchAlgorithmException {
        EncryptionDao encryptionDao = new EncryptionDao();
        User user2 = new User();
        user2.setUsername("abcd");
        user2.setPassword("pass123");
        RegisterDao registerDao = new RegisterDao();
        registerDao.addUser(user2);
        userDao = new UserDao();
        String salt = encryptionDao.getUserSalt(user2);
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        byte[] encPass = passwordEncryptionService.getEncryptedPassword(user2.getPassword(), Base64.getDecoder().decode(salt));
        String passFromDb = userDao.getUserPasswordFromDatabase(user2);
        userDao.deleteUser(user2);
        assertEquals(Base64.getEncoder().encodeToString(encPass), passFromDb);
    }

    @Test
    public void testDeleteUser() {
        UserDao userDao1 = new UserDao();
        User user2 = new User();
        user2.setPassword("123f");
        user2.setUsername("Drake");
        user2.setScore(129);
        registerDao = new RegisterDao();
        registerDao.addUser(user2);
        userDao1.deleteUser(user2);
        assertEquals("No user found", userDao1.getUsernameFromDatabase(user2));
    }

    @AfterEach
    public void end() {
        UserDao userDao = new UserDao();
        userDao.deleteUser(user);
    }
}