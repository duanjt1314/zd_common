package cn.zdsoft.common.model;

import java.util.LinkedHashMap;

/**
 * 数据行
 * @author Administrator
 *
 */
public class DataRow extends LinkedHashMap<String, Object>{

	@Override
	public Object put(String key, Object value) {
		// TODO Auto-generated method stub
		return super.put(key.toUpperCase(), value);
	}

	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		return super.get(key.toString().toUpperCase());
	}
	
	

}
