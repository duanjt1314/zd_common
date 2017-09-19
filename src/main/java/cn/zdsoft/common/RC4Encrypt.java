package cn.zdsoft.common;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * RC4加密解密
 * @author 段江涛
 * @date 2017-08-30
 */
public class RC4Encrypt {
	/**
     * ALGORITHM 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     * 
     * <pre>
     * DES                  key size must be equal to 56 
     * DESede(TripleDES)    key size must be equal to 112 or 168 
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
     * RC2                  key size must be between 40 and 1024 bits 
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
     * </pre>
     * 
     * 在Key toKey(byte[] key)方法中使用下述代码
     * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
     * <code> 
     * DESKeySpec dks = new DESKeySpec(key); 
     * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
     * SecretKey secretKey = keyFactory.generateSecret(dks); 
     * </code>
     */
	public static String ALGORITHM = "RC4";
	
	/**
     * 转换密钥<br>
     * 
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = null;
        if (ALGORITHM.equalsIgnoreCase("des")) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(ALGORITHM);
            secretKey = keyFactory.generateSecret(dks);
        } else {
            // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
            secretKey = new SecretKeySpec(key, ALGORITHM);
        }
        return secretKey;
    }
    
	/**
     * 解密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        //Key k = toKey(decryptBASE64(key));
    	Key k = toKey(key.getBytes());
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        //Key k = toKey(decryptBASE64(key));
    	Key k = toKey(key.getBytes());
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }
    
    public static byte[] decryptBASE64(String data) {
        return Base64.decodeBase64(data);
    }

    public static String encryptBASE64(byte[] data) {
        return new String(Base64.encodeBase64(data));
    }
}
