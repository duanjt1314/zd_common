package cn.zdsoft.common;

import java.security.MessageDigest;

/**
 * MD5加密
 * @author 段江涛
 * @date 2017-09-19
 */
public class MD5Util {
	/**
	 * 对内容进行MD5加密
	 * @param content
	 * @return
	 */
	public static String Encrypt(String content) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = content.getBytes("gb2312");
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			String result = hexValue.toString();
			//result = result.substring(0, result.length() - 2).toUpperCase();
			result = result.toUpperCase();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
	}
}
