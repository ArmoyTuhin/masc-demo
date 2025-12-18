package demo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
public class Encryptor {
    
    private CryptoConfig config;
    private SecurityProfile profile;
    
    public Encryptor(CryptoConfig config, SecurityProfile profile) {
        this.config = config;
        this.profile = profile;
    }
    
    /**
     * Gets the security profile.
     */
    public SecurityProfile getProfile() {
        return profile;
    }
    
    /**
     * Encrypts data using the configured cipher.
     * Warning: ECB mode doesn't use IV, which is insecure for repeated data.
     */
    public byte[] encrypt(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance(config.getTransformation());
            cipher.init(Cipher.ENCRYPT_MODE, config.getSecretKey());
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    /**
     * Decrypts data using the configured cipher.
     */
    public byte[] decrypt(byte[] ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance(config.getTransformation());
            cipher.init(Cipher.DECRYPT_MODE, config.getSecretKey());
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
    
    /**
     * Encrypts with CBC mode (requires IV).
     * Note: IV should be random and unique for each encryption.
     */
    public byte[] encryptWithCBC(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            
            // Generate IV
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            cipher.init(Cipher.ENCRYPT_MODE, config.getSecretKey(), ivSpec);
            byte[] encrypted = cipher.doFinal(plaintext);
            
            // Prepend IV to ciphertext (common practice)
            byte[] result = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, result, 0, iv.length);
            System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
            
            return result;
        } catch (Exception e) {
            throw new RuntimeException("CBC encryption failed", e);
        }
    }
    
    /**
     * Decrypts CBC-encrypted data.
     */
    public byte[] decryptWithCBC(byte[] ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            
            // Extract IV from ciphertext
            byte[] iv = new byte[16];
            System.arraycopy(ciphertext, 0, iv, 0, 16);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            // Extract encrypted data
            byte[] encrypted = new byte[ciphertext.length - 16];
            System.arraycopy(ciphertext, 16, encrypted, 0, encrypted.length);
            
            cipher.init(Cipher.DECRYPT_MODE, config.getSecretKey(), ivSpec);
            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("CBC decryption failed", e);
        }
    }
}

