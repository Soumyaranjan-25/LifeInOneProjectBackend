package com.lio.config;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
	
//	 private static final String secretKey = "a5a9671c8f8b41d1b48e409c07e9e384";
	    private static final String SECRET_KEY = "a5a9671c8f8b41d1b48e409c07e9e384";
	    private static final String ALGORITHM = "AES";


//	    public String encryptData(String data) {
//	        try {
//	            SecretKey key = new SecretKeySpec(secretKey.getBytes(), "AES");
////	            Cipher cipher = Cipher.getInstance("AES");
//	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//
//	            cipher.init(Cipher.ENCRYPT_MODE, key);
//	            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
//	            return Base64.getEncoder().encodeToString(encryptedBytes);
//	        } catch (Exception e) {
//	            throw new RuntimeException("Encryption failed", e);
//	        }
//	    }

//	    public String decryptData(String encryptedData) {
//	        try {
//	            SecretKey key = new SecretKeySpec(secretKey.getBytes(), "AES");
////	            Cipher cipher = Cipher.getInstance("AES");
//	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//
//	            cipher.init(Cipher.DECRYPT_MODE, key);
//	            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
//	            return new String(decryptedBytes);
//	        } catch (Exception e) {
//	            throw new RuntimeException("Decryption failed", e);
//	        }
//	    }
	    
	    public String decryptData(String encryptedData) throws Exception {
	        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
	        return new String(decryptedBytes);
	    }
}
