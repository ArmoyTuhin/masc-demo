package demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Security profile configuration.
 * Contains various security-related settings and operations.
 */
public class SecurityProfile {
    
    private String profileName;
    private int keySize;
    private String hashAlgorithm;
    
    public SecurityProfile(String profileName, int keySize, String hashAlgorithm) {
        this.profileName = profileName;
        this.keySize = keySize;
        this.hashAlgorithm = hashAlgorithm;
    }
    
    /**
     * Computes a hash of the input data.
     * Note: MD5 and SHA-1 are considered weak and should be avoided.
     */
    public byte[] computeHash(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance(hashAlgorithm);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash algorithm not available: " + hashAlgorithm, e);
        }
    }
    
    /**
     * Gets the maximum allowed key size for this profile.
     */
    public int getKeySize() {
        return keySize;
    }
    
    /**
     * Sets the key size for this profile.
     */
    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }
    
    /**
     * Gets the hash algorithm used in this profile.
     */
    public String getHashAlgorithm() {
        return hashAlgorithm;
    }
    
    /**
     * Gets the profile name.
     */
    public String getProfileName() {
        return profileName;
    }
}

