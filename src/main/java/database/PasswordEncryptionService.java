package database;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordEncryptionService {

    public byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String encryptionType = "PBKDF2WithHmacSHA1";
        int keyLength = 160;
        int iterations = 20000;
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(encryptionType);
        return secretKeyFactory.generateSecret(keySpec).getEncoded();
    }
    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        sr.nextBytes(salt);
        return salt;
    }
    public boolean securityCheck(String password, byte[] encryptedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        byte[] attempt = getEncryptedPassword(password, salt);
        if(Arrays.equals(attempt, encryptedPassword))
            return true;
        else
            return false;
    }
}
