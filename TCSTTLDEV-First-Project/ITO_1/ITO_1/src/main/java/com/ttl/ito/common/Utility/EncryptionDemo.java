package com.ttl.ito.common.Utility;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author zchumager
 */
public class EncryptionDemo {
	
	private static final String ALGORITHM = "AES";
	private static final String myEncryptionKey = "ThisIsFoundation";
	private static final String UNICODE_FORMAT = "UTF8";

	public static void main(String[] args) throws Exception {
		String originalPassword = "Test@123";
		System.out.println("Original password: " + originalPassword);
		String encryptedPassword = encrypt(originalPassword);
		System.out.println("Encrypted password: " + encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword);
		System.out.println("Decrypted password: " + decryptedPassword);
	}

	public static String encrypt(String valueToEnc) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encValue);
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws Exception {

		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		String decordedValue = new BASE64Decoder().decodeBuffer(encryptedData).toString().trim();
		return decordedValue;
	}

	private static Key generateKey() throws Exception {
		byte[] keyAsBytes;
		keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		Key key = new SecretKeySpec(keyAsBytes, ALGORITHM);
		return key;
	}
}