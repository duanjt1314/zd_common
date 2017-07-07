package cn.zdsoft.common;

import java.util.regex.Pattern;

public class RegexUtil {
	/**
	 * 通过正则表达式和内容验证是否匹配
	 * @param regex	正则表达式字符串
	 * @param content	需要验证的内容
	 * @return	是否匹配成功
	 */
	public static boolean Regex(String regex,String content){
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(content).matches();
	}
}
