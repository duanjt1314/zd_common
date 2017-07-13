package cn.zdsoft.common;

import java.io.File;

/**
 * 目录公共类
 * 
 * @author 段江涛
 * @date 2017-07-12
 */
public class DirectoryUtil {
	/**
	 * 创建目录,如果目录已存在则不处理
	 * 
	 * @param dirName
	 *            目录名称
	 */
	public static void CreateDir(String dirName) {
		File file=new File(dirName);
		if(file.isFile()||file.exists()){
			return;
		}
		file.mkdirs();
	}
}
