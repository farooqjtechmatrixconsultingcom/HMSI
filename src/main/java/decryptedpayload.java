import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.Base64;

public class decryptedpayload {
	
    public static String decrypt(String key, String salt, String encryptedString) throws Exception {
    // Generate the secret key
    byte[] saltBytes = salt.getBytes();
    byte[] keyBytes = (key + new String(saltBytes)).getBytes("UTF-8");
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    keyBytes = md.digest(keyBytes);
    Key secretKey = new SecretKeySpec(keyBytes, "AES");

    // Initialize the cipher for decryption
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] ivBytes = new byte[cipher.getBlockSize()];
    IvParameterSpec iv = new IvParameterSpec(ivBytes);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

    // Decrypt the string
    byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
    byte[] decryptedTextBytes = cipher.doFinal(encryptedBytes);
    String decryptedText = new String(decryptedTextBytes, "UTF-8");
    return decryptedText;
}
}