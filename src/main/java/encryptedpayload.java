import javax.crypto.spec.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class encryptedpayload {

    public static String encrypt(String key, String salt, String unencryptedString) throws Exception {
        // Generate the secret key
        byte[] saltBytes = salt.getBytes();
        byte[] keyBytes = (key + new String(saltBytes)).getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        keyBytes = md.digest(keyBytes);
        Key secretKey = new SecretKeySpec(keyBytes, "AES");

        // Initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] ivBytes = new byte[cipher.getBlockSize()];
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        // Encrypt the string
        byte[] encryptedTextBytes = cipher.doFinal(unencryptedString.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedTextBytes);
        return encryptedText;
    }

}