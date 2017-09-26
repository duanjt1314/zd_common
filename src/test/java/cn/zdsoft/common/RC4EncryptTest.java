package cn.zdsoft.common;

import static org.junit.Assert.*;

import java.util.Base64;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class RC4EncryptTest {

	/**
	 * 解密
	 * @throws Exception 
	 */
	
	public void testDecrypt() throws Exception {
//		String content="我是中国人";
//		byte[] bs=content.getBytes("utf-8");
//		bs=RC4Encrypt.encrypt(bs, "hzsoft|hzsoft|hzsoft|hzsoft|hzsoft|hzsoft|hzsoft|hzsoft|hzsoft");
//		System.out.println(Base64.getEncoder().encodeToString(bs));
		
		String content="我是中国人";
		String res= Base64.getEncoder().encodeToString(content.getBytes("utf-8"));
		System.out.println(new BASE64Encoder().encode(content.getBytes("utf8")));
		System.out.println(res);
		
	}

	/**
	 * 加密
	 * @throws Exception 
	 */
	
	public void testEncrypt() throws Exception {
		String content="我是中国人";
		byte[] bs=content.getBytes("utf-8");
		bs=RC4Encrypt.decrypt(bs, "hzsoft|hzsoft|hzsoft");
		
		bs=RC4Encrypt.decrypt(bs, "hzsoft|hzsoft|hzsoft");
		System.out.println(new String(bs));
	}

}
