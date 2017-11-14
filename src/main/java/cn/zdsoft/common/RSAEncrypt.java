package cn.zdsoft.common;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAEncrypt {
	private String private_key = "";
	private String public_key = "";

	public RSAEncrypt(String pri_key, String pub_key) {
		this.private_key = pri_key;
		this.public_key = pub_key;
	}

	/**
	 * 得到公钥
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.getDecoder().decode(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.getDecoder().decode(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	/**
	 * 加密
	 * @param bt_plaintext
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] bt_plaintext)throws Exception{  
        PublicKey publicKey = getPublicKey(this.public_key);  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        byte[] bt_encrypted = cipher.doFinal(bt_plaintext);  
        return bt_encrypted;  
    }  
	
	/**
	 * 解密
	 * @param bt_encrypted
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] bt_encrypted)throws Exception{  
        PrivateKey privateKey = getPrivateKey(private_key);  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        byte[] bt_original = cipher.doFinal(bt_encrypted);  
        return bt_original;  
    }  
}
