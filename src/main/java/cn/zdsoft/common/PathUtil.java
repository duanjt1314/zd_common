package cn.zdsoft.common;

import java.io.File;

public class PathUtil {
	/**
	 * 获取文件名但不包含后缀名
	 * 
	 * @param url
	 *            文件路径
	 * @return
	 */
	public static String GetFileNameWithoutExtension(String url) {
		int index = url.lastIndexOf('.');
		if (index == -1) {
			return "";
		}
		return url.substring(index + 1);
	}

	/**
	 * 获取文件名称
	 * 
	 * @param url
	 *            文件路径
	 * @return
	 */
	public static String GetFileName(String url) {
		File file = new File(url);
		return file.getName();
	}

	/**
	 * 将多个路径拼接为一个路径
	 * @param strs
	 * @return
	 */
	public static String Combine(String... strs) {
		String result = "";
		for (int i = 0; i < strs.length; i++) {
			if (i == strs.length - 1) {
				result += strs[i];
			} else {
				result += strs[i] + File.separator;
			}
		}
		return result;
	}
}
