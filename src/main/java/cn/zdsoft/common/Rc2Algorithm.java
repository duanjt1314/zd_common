package cn.zdsoft.common;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Rc2Algorithm {
	public static final String ALGORITHM_DES = "DES";
	public static final String ALGORITHM_DESede = "DESede";
	public static final String ALGORITHM_AES = "AES";
	public static final String ALGORITHM_Blowfish = "Blowfish";
	public static final String ALGORITHM_RC2 = "RC2/CBC/PKCS7Padding";
	public static final String ALGORITHM_RC4 = "RC4";

	private static Key toKey(String algorithm, byte[] key)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException {
		if (ALGORITHM_DES.equals(algorithm)) {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
			SecretKey secretKey = keyFactory.generateSecret(dks);
			return secretKey;
		}

		return new SecretKeySpec(key, algorithm);
	}

	public static byte[] decrypt(String algorithm, byte[] data, byte[] key)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		Key k = toKey(algorithm, key);
		byte[] iv = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		RC2ParameterSpec rc2Spec = new RC2ParameterSpec(40, iv, 0);
		Cipher cipher = Cipher.getInstance(algorithm, new org.bouncycastle.jce.provider.BouncyCastleProvider());
		cipher.init(Cipher.DECRYPT_MODE, k, rc2Spec);
		return cipher.doFinal(data);
	}

	public static byte[] encrypt(String algorithm, byte[] data, byte[] key)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, GeneralSecurityException {
		Key k = toKey(algorithm, key);
		byte[] iv = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		RC2ParameterSpec rc2Spec = new RC2ParameterSpec(40, iv, 0);
		Cipher cipher = Cipher.getInstance(algorithm, new org.bouncycastle.jce.provider.BouncyCastleProvider());
		cipher.init(Cipher.ENCRYPT_MODE, k, rc2Spec);
		return cipher.doFinal(data);

	}

	public static byte[] initKey(String algorithm) throws NoSuchAlgorithmException {
		return initKey(algorithm, null);
	}

	public static byte[] initKey(String algorithm, byte[] seed) throws NoSuchAlgorithmException {
		SecureRandom secureRandom = null;

		if (seed != null) {
			secureRandom = new SecureRandom(seed);
		} else {
			secureRandom = new SecureRandom();
		}

		KeyGenerator kg = KeyGenerator.getInstance(algorithm.toString());
		kg.init(secureRandom);

		return kg.generateKey().getEncoded();
	}
}
