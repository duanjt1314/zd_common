package cn.zdsoft.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间帮助类，统一使用java.util.date
 * 
 * @author Administrator
 *
 */
public class DateUtil {
	/**
	 * 将日期按指定时间格式格式化
	 * 
	 * @param date
	 *            待格式化日期
	 * @param format
	 *            格式化样式
	 * @return 格式化后的字符串
	 */
	public static String Format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将日期按指定时间格式格式化,默认格式化样式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            待格式化日期
	 * @return 格式化后的字符串
	 */
	public static String Format(Date date) {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
