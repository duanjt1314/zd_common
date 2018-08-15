package cn.zdsoft.common.model;

import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * 数据行
 * @author Administrator
 * HashMap是无序的，LinkedHashMap是有序的(取数据时先插入先取出)
 */
public class DataRow extends LinkedCaseInsensitiveMap{

	public Object put(String key, Object value) {
		return super.put(key, value);
	}

	public Object get(Object key) {
		return super.get(key);
	}
	
}
