package cn.zdsoft.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

/**
 * 文件管理，快速读写文件
 * 
 * @author Administrator
 *
 */
public class FileUtil {
	/**
	 * 将指定路径文件读取为字符串
	 * 
	 * @param url
	 *            文件路径
	 * @return 文件内容字符串
	 */
	public static String ReadAllString(String url) {
		try {
			byte[] bs = ReadAllByte(url);
			return new String(bs, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static byte[] ReadAllByte(String url) throws IOException {
		java.io.File file = new java.io.File(url, "");
		FileInputStream fis = new FileInputStream(file);

		long len = file.length();
		byte[] bytes = new byte[(int) len];
		fis.read(bytes, 0, bytes.length);
		fis.close();
		return bytes;
	}

	/**
	 * 将指定内容全部写入指定路径，如果文件已存在、新内容将覆盖之前内容
	 * 
	 * @param url
	 *            写入路径
	 * @param content
	 *            待写入内容
	 * @throws IOException
	 */
	public static void WriteAllString(String url, String content) throws IOException {
		FileOutputStream fos = new FileOutputStream(url);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		osw.write(content);
		osw.close();
		fos.close();
		
		/**
		 * 备忘录,也可以使用FileWriter来实现
		 */
		// FileWriter writer=new FileWriter("f:\\aa.txt");
		// writer.write("追加内容\r\n");
		// writer.close();
	}

	/**
	 * 将指定字节数组写入指定路径
	 * 
	 * @param url
	 *            写入路径
	 * @param bytes
	 *            待写入字节数组
	 * @throws IOException
	 */
	public static void WriteAllByte(String url, byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream(url);
		fos.write(bytes);
		fos.close();
	}

	/**
	 * 删除目录以及目录下的子目录、子文件
	 * 
	 * @param path
	 *            目录
	 * @return
	 */
	public static boolean DeleteDir(String path) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		// if(!path.endsWith(File.separator)){
		// path = path+File.separator;
		// }

		File dirFile = new File(path);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			System.out.println("删除目录失败[" + path + "]目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = new File(files[i].getAbsolutePath()).delete();
				if (!flag) {
					System.out.println("文件删除失败:[" + files[i].getAbsolutePath() + "] 可能是流没有正确关闭");
					break;
				}
			}
			// 删除子目录
			else {
				flag = DeleteDir(files[i].getAbsolutePath());
				if (!flag) {
					System.out.println("文件夹删除失败:" + files[i].getAbsolutePath());
					break;
				}
			}
		}

		if (!flag) {
			System.out.println("删除目录失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * @param fileName 文件全路径
	 * @return
	 */
	public static boolean DeleteFile(String fileName){
		File file=new File(fileName);
		return file.delete();
	}
	
	/**
	 * 查找指定目录下包含关键字的所有文件信息
	 * 
	 * @param dirName
	 *            目录全路径
	 * @param keyWord
	 *            关键字
	 * @param ignoreCase
	 *            是否忽略大小写
	 * @return
	 */
	public static File[] GetFiles(String dirName, String keyWord, boolean ignoreCase) {
		File file = new File(dirName);
		if (!file.isDirectory()) {
			throw new RuntimeException("目录:" + dirName + " 不存在");
		}

		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (ignoreCase) {
					// 判断是否包含关键字
					if (f.getName().toLowerCase().indexOf(keyWord.toLowerCase()) >= 0) {
						return true;
					} else {
						return false;
					}
				} else {
					// 判断是否包含关键字
					if (f.getName().indexOf(keyWord) >= 0) {
						return true;
					} else {
						return false;
					}
				}
			}
		});

		return files;
	}

	/**
	 * 查找指定目录下包含关键字的所有文件信息(不会忽略大小写)
	 * 
	 * @param dirName
	 *            目录全路径
	 * @param keyWord
	 *            关键字
	 * @return
	 */
	public static File[] GetFiles(String dirName, String keyWord) {
		return GetFiles(dirName, keyWord, false);
	}

	/**
	 * 根据给定的关键字数组查找指定目录下的文件，只要在关键字数组中有一个关键字满足条件就返回该文件，同时可配置是否忽略大小写
	 * 
	 * @param dirName
	 *            目录全路径
	 * @param keyWords
	 *            关键字数组
	 * @param ignoreCase
	 *            是否忽略大小写
	 * @return
	 */
	public static File[] GetFiles(String dirName, String[] keyWords, boolean ignoreCase) {
		File file = new File(dirName);
		if (!file.isDirectory()) {
			throw new RuntimeException("目录:" + dirName + " 不存在");
		}

		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				for (String keyWord : keyWords) {
					if (ignoreCase) {
						// 判断是否包含关键字
						if (f.getName().toLowerCase().indexOf(keyWord.toLowerCase()) >= 0) {
							return true;
						}
					} else {
						// 判断是否包含关键字
						if (f.getName().indexOf(keyWord) >= 0) {
							return true;
						}
					}
				}
				return false;
			}
		});

		return files;
	}

	/**
	 * 根据给定的关键字数组查找指定目录下的文件，只要在关键字数组中有一个关键字满足条件就返回该文件(不会忽略大小写)
	 * 
	 * @param dirName
	 *            目录全路径
	 * @param keyWords
	 *            关键字数组
	 * @return
	 */
	public static File[] GetFiles(String dirName, String[] keyWords) {
		return GetFiles(dirName, keyWords, false);
	}

	/**
	 * 根据正则表达式筛选文件
	 * 
	 * @param dirName
	 *            目录全路径
	 * @param regex
	 *            正则表达式
	 * @return
	 */
	public static File[] GetFilesRegex(String dirName, String regex) {
		File file = new File(dirName);
		if (!file.isDirectory()) {
			throw new RuntimeException("目录:" + dirName + " 不存在");
		}

		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				// 判断是否验证通过
				return RegexUtil.Regex(regex, f.getName());
			}
		});

		return files;
	}

	/**
	 * 获取指定目录下的所有文件
	 * 
	 * @param dirName
	 * @return
	 */
	public static File[] GetFiles(String dirName) {
		File file = new File(dirName);
		if (!file.isDirectory()) {
			throw new RuntimeException("目录:" + dirName + " 不存在");
		}

		return file.listFiles();
	}

	/**
	 * 文件移动
	 * 
	 * @param fileName
	 *            源文件全路径
	 * @param targetFileName
	 *            目标文件全路径
	 * @throws IOException 
	 */
	public static void CopyFile(String fileName, String targetFileName) throws IOException {
		File oldFile = new File(fileName);
		File newFile = new File(targetFileName);

		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(newFile);

		byte[] buffer = new byte[2097152];
		while ((in.read(buffer)) != -1) {
			out.write(buffer);
		}
		
		in.close();
		out.close();
	}
	
	/**
	 * 判断文件是否被占用 被占用返回true
	 * @param file
	 * @return 
	 */
	public static boolean isFileInUse(File file) {
		if (file.renameTo(file)) {
			return false;
		} else {
			return true;
		}
	}


}
