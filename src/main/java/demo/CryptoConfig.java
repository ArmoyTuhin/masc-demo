package demo;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Configuration class for cryptographic operations.
 * This class may contain crypto misuses that can be detected by tools like CogniCrypt.
 */
public class CryptoConfig {
    
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    
    private SecretKey secretKey;
    
    /**
     * Generates a secret key for encryption.
     * Note: ECB mode is generally insecure for most use cases.
     */
    public void generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(128); // Key size
            this.secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate key", e);
        }
    }
    
    /**
     * Creates a key from a byte array.
     * Warning: Using hardcoded or weak keys is a security risk.
     */
    public void setKeyFromBytes(byte[] keyBytes) {
        this.secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
    }
    
    /**
     * Gets the configured secret key.
     */
    public SecretKey getSecretKey() {
        return secretKey;
    }
    
    /**
     * Gets the encryption algorithm.
     */
    public String getAlgorithm() {
        return ALGORITHM;
    }
    
    /**
     * Gets the cipher transformation.
     */
    public String getTransformation() {
        return TRANSFORMATION;
    }
}

