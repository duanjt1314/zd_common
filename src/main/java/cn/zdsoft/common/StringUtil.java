package cn.zdsoft.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import cn.zdsoft.common.model.DataRow;
import cn.zdsoft.common.model.DataTable;

/**
 * 字符串管理类
 * 
 * @author Administrator
 *
 */
public class StringUtil {
	static Gson gson = new Gson();

	/**
	 * 判断指定字符串是否是空或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean IsNullOrEmpty(String str) {
		if (str == null || str == "" || str.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 将对象序列化为Json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String GetJsonString(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 将字符串转换为Map键值对的集合
	 * 备注:所有的标头将强制转换为大写
	 * 
	 * @param str
	 * @return
	 */
	public static DataTable String2Map(String str) {
		DataTable list = new DataTable();
		if (IsNullOrEmpty(str)) {
			return list;
		}
		// 定义列
		List<String> columnNames = new ArrayList<String>();
		String[] rows = str.split(System.lineSeparator());
		if (rows.length > 0) {
			// 实例化列
			String[] cls = rows[0].split("\t");// 第一行代表标题
			for (int i = 0; i < cls.length; i++) {
				if (!IsNullOrEmpty(cls[i].trim().toUpperCase()))
					columnNames.add(cls[i].trim().toUpperCase());
			}

			// 实例化数据
			for (int i = 1; i < rows.length; i++) {
				DataRow hash = new DataRow();
				cls = rows[i].split("\t");
				for (int m = 0; m < cls.length; m++) {
					if (!IsNullOrEmpty(cls[m].trim())) {
						hash.put(columnNames.get(m), cls[m].trim());
					}
				}
				list.add(hash);
			}
		}

		return list;
	}

	/**
	 * 将Map键值对集合按制表符和\t转换为字符串（包含标题头部）
	 * 
	 * @param list
	 * @return
	 */
	public static String Map2String(DataTable list) {
		return Map2String(list, true);
	}

	/**
	 * 将Map键值对集合按制表符和\t转换为字符串，可指定是否包含标题头部
	 * @param list 需要转换的Map键值对集合
	 * @param containTitle 是否包含标题头部
	 * @return
	 */
	public static String Map2String(DataTable list, Boolean containTitle) {
		StringBuilder sb = new StringBuilder();
		if (list == null || list.size() == 0) {
			return sb.toString();
		}

		if (containTitle) {
			// 标头
			for (String key : list.get(0).keySet()) {
				sb.append(key + "\t");
			}
			sb.delete(sb.length() - 1, sb.length());// 删除最后一个制表符
			sb.append(System.lineSeparator());
		}

		for (int i = 0; i < list.size(); i++) {
			for (String key : list.get(i).keySet()) {
				sb.append(list.get(i).get(key) + "\t");
			}
			sb.delete(sb.length() - 1, sb.length());// 删除最后一个制表符
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}
	
	/**
	 * 去掉字符串末尾指定的字节
	 * @param content
	 * @param cha
	 * @return
	 */
	public static String ReplaceLastStr(String content,char cha){
		String c=new String(new char[]{cha});
		while(content.substring(content.length()-1).equals(c)){
			content=content.substring(0,content.length()-1);
		}
		return content;
	}
}
