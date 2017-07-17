package cn.zdsoft.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Zip文件的压缩和解压
 * 
 * @author Administrator
 *
 */
public class ZipUtil {
	/**
	 * 压缩zip文件
	 * 
	 * @param url 压缩源目录或文件
	 * @param zipDir 压缩目标zip目录
	 * @param zipName 压缩目标zip名称
	 * @param containsDir 是否包含目录，当url为目录时有效
	 * @throws IOException
	 */
	public static void Zip(String url, String zipDir, String zipName, Boolean containsDir) throws IOException {
		// 判断目标目录并创建
		File zipDirFile = new File(zipDir);
		if (!zipDirFile.exists() || !zipDirFile.isDirectory()) {
			zipDirFile.mkdir();// 创建目录
		}

		String zipPath = zipDir + File.separator + zipName;
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
		java.io.File file = new File(url);
		if (file.isDirectory()) {
			if (containsDir == true) {
				Zip(file.getParent(), file, out);
			} else {
				Zip(file.getPath() + File.separator, file, out);
			}
		} else {
			Zip(file.getParent(), file, out);
		}
		out.close();
	}

	/**
	 * 将指定的文件/文件夹列表集合压缩到指定目录
	 * @param files 待压缩文件的集合
	 * @param zipDir 压缩后的目录
	 * @param zipName 压缩后的文件名
	 * @param containsDir 是否包含文件的目录
	 * @throws IOException
	 */
	public static void Zip(List<File> files, String zipDir, String zipName,Boolean containsDir) throws IOException {
		// 判断目标目录并创建
		File zipDirFile = new File(zipDir);
		if (!zipDirFile.exists() || !zipDirFile.isDirectory()) {
			zipDirFile.mkdir();// 创建目录
		}

		String zipPath = zipDir + File.separator + zipName;
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
		
		for (File file : files) {
			if (file.isDirectory()) {
				if (containsDir == true) {
					Zip(file.getParent(), file, out);
				} else {
					Zip(file.getPath() + File.separator, file, out);
				}
			} else {
				Zip(file.getParent(), file, out);
			}
		}
		
		out.close();
	}

	private static void Zip(String baseDir, java.io.File file, ZipOutputStream out) throws IOException {
		if (file.isDirectory()) {
			// 表示目录，继续递归
			for (java.io.File f : file.listFiles()) {
				Zip(baseDir, f, out);
			}
		} else {
			// 表示文件，直接压缩
			/**
			 * 1.加载名称，相对路径 2.写入数据，需要绝对路径
			 */
			System.out.println("准备压缩:" + file.getPath());
			out.putNextEntry(new ZipEntry(file.getPath().replace(baseDir, "")));
			out.write(cn.zdsoft.common.FileUtil.ReadAllByte(file.getPath()));
		}

	}

	/**
	 * 将指定的zip文件解压到指定目录
	 * 
	 * @param url
	 *            需要解压的压缩文件
	 * @param path
	 *            解压目标目录
	 * @param containsFileName
	 *            解压后是否包含文件名
	 * @throws IOException
	 * @throws ZipException
	 */
	public static void UnZip(String url, String path, Boolean containsFileName) throws ZipException, IOException {
		File urlFile = new File(url);
		if (!urlFile.exists()) {
			throw new RuntimeException(url + "所指文件不存在");
		}
		if (containsFileName == true) {
			path += File.separator + PathUtil.GetFileNameWithoutExtension(url);
		}
		File pathFile = new File(path);
		if (!pathFile.isDirectory() || !pathFile.exists()) {
			pathFile.mkdir();// 创建目录
		}

		ZipFile zipFile = new ZipFile(urlFile);
		Enumeration entries = zipFile.entries();
		ZipEntry entry = null;

		while (entries.hasMoreElements()) {
			entry = (ZipEntry) entries.nextElement();
			System.out.println("解压" + entry.getName());
			if (entry.isDirectory()) {
				// 表示目录
				String dirPath = path + File.separator + entry.getName();
				File dir = new File(dirPath);
				dir.mkdirs();
			} else {
				// 表示文件
				File f = new File(path + File.separator + entry.getName());
				if (!f.exists()) {
					String dirs = f.getParent();
					File parentDir = new File(dirs);
					parentDir.mkdirs();
				}

				f.createNewFile();
				// 将压缩文件内容写入到这个文件中
				InputStream is = zipFile.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(f);

				int count;
				byte[] buf = new byte[8192];
				while ((count = is.read(buf)) != -1) {
					fos.write(buf, 0, count);
				}
				is.close();
				fos.close();
			}
		}
		zipFile.close();

	}
}
