package cn.zdsoft.common.model;

import org.apache.commons.collections.map.CaseInsensitiveMap;

/**
 * 数据行
 * @author Administrator
 * HashMap是无序的，LinkedHashMap是有序的(取数据时先插入先取出)
 */
public class DataRow extends CaseInsensitiveMap{

	public Object put(String key, Object value) {
		return super.put(key, value);
	}

	public Object get(Object key) {
		return super.get(key);
	}
	
}
