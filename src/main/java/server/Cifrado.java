package server;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Cifrado {

	private static String KEY = "LaWeaHTTP1234567";

	public static String Encrypt(String pInput) {
		String encryptedtext = null;
		try {

			String Input = pInput;

			SecretKeySpec aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(Input.getBytes());
			encryptedtext = DatatypeConverter.printBase64Binary(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedtext;
	}

	public static String Decrypt(String pInput) {

		byte[] encrypted;

		String decrypted = null;

		try {

			SecretKeySpec aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, aesKey);

			encrypted = DatatypeConverter.parseBase64Binary(pInput);
			decrypted = new String(cipher.doFinal(encrypted));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypted;
	}
}
