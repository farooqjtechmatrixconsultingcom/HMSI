import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class decodepayload {

    // Function to decrypt the data
    public static String decrypt(String encryptedData, String secretKey, String iv) throws Exception {
        // Convert the secret key and IV into byte arrays
        byte[] key = secretKey.getBytes("UTF-8");
        byte[] ivBytes = iv.getBytes("UTF-8");

        // Create SecretKeySpec for the AES encryption key
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        // Create IvParameterSpec for the initialization vector (IV)
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Create Cipher instance for AES decryption in CBC mode with PKCS5 padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Decode the Base64 encoded encrypted data to get the byte array
        byte[] decodedEncryptedData = Base64.getDecoder().decode(encryptedData);

        // Decrypt the data
        byte[] decryptedData = cipher.doFinal(decodedEncryptedData);

        // Convert the decrypted data back to a string
        return new String(decryptedData, "UTF-8");
    }
}