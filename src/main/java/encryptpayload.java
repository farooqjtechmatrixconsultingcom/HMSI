import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class encryptpayload {

    // Function to encrypt the data
    public static String encrypt(String data, String secretKey, String iv) throws Exception {
        // Convert the secret key and IV into byte arrays
        byte[] key = secretKey.getBytes("UTF-8");
        byte[] ivBytes = iv.getBytes("UTF-8");

        // Create SecretKeySpec for the AES encryption key
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        // Create IvParameterSpec for the initialization vector (IV)
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Create Cipher instance for AES encryption in CBC mode with PKCS7 padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Initialize the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Encrypt the data
        byte[] encryptedData = cipher.doFinal(data.getBytes("UTF-8"));

        // Encode the encrypted data as Base64 and return it as a string
        return Base64.getEncoder().encodeToString(encryptedData);
    }

}