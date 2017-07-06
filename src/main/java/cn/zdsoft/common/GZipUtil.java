package cn.zdsoft.common;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.*;

/**
 * GZ文件的解压和压缩
 * 
 * @author Administrator
 *
 */
public class GZipUtil {
	/**
	 * 解压GZ文件
	 * 
	 * @param url
	 *            文件路径
	 * @return 文件内容字符串
	 * @throws IOException
	 */
	public static String UnCompress(String url) throws IOException {
		if (StringUtil.IsNullOrEmpty(url)) {
			return "";
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream fis = new FileInputStream(url);
		GZIPInputStream gunzip = new GZIPInputStream(fis);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		gunzip.close();
		fis.close();
		out.close();
		// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
		return out.toString();
	}

	/**
	 * 通过GZ压缩方式将指定内容压缩到指定路径
	 * 
	 * @param content
	 * @param url
	 * @throws Exception
	 *             参数为空将抛出异常
	 */
	public static void Compress(String content, String url) throws Exception {
		if (StringUtil.IsNullOrEmpty(content)) {
			throw new Exception("参数content不能为空");
		}
		if (StringUtil.IsNullOrEmpty(url)) {
			throw new Exception("参数url不能为空");
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(content.getBytes());
		gzip.close();
		FileUtil.WriteAllByte(url,out.toByteArray());
	}

}
