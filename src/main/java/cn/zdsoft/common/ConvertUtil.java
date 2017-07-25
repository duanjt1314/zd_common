package cn.zdsoft.common;

/**
 * 格式转换公共类库
 * 
 * @author 段江涛
 * @date 2017-07-11
 */
public class ConvertUtil {
	public static <T> T Convert(Object obj, Class<T> classType) {
		if (classType == Boolean.class) {
			// 真假
			return classType.cast(Boolean.parseBoolean(obj.toString()));
		} else if (classType == Integer.class) {
			// 整型
			return classType.cast(Integer.parseInt(obj.toString()));
		} else if (classType == Double.class) {
			//Double
			return classType.cast(Double.parseDouble(obj.toString()));
		} else {
			// 默认 字符串
			return classType.cast(obj);			
		}
	}
}
