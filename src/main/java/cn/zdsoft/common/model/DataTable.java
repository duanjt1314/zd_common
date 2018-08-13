package cn.zdsoft.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模拟实现DataTable相关功能
 * 
 * @author 段江涛
 *
 */
public class DataTable extends ArrayList<DataRow> {
	private String[] columns;
	private String tableName;

	public DataTable(){}
	
	public DataTable(List<Map<String,Object>> list){
		for (Map<String,Object> map : list) {
			DataRow row=new DataRow();
			for (Object key : map.keySet()) {
				row.put(key, map.get(key));
			}
			this.add(row);
		}
	}
		
	/**
	 * 当前对象是否为空或数据条数为0
	 * 
	 * @return
	 */
	public boolean isNullOrEmpty() {
		if (this == null || this.size() == 0)
			return true;
		return false;
	}

	/**
	 * 获得列的集合
	 * 
	 * @return
	 */
	public String[] getColumns() {
		if (this.columns == null) {
			if (!isNullOrEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Object s : this.get(0).keySet()) {
					list.add(s.toString());
				}
				this.columns = list.toArray(new String[list.size()]);
			} else {
				this.columns = new String[] {};
			}
		}
		return this.columns;
	}

	/**
	 * 设置DataTable列的集合
	 */
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	
	/**
	 * @return 获取 tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 设置 tableName
	 * @param 
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 复制一个全新的DataTable
	 * @return
	 */
	public DataTable copy() {
		DataTable table = new DataTable();
		for (DataRow dataRow : this) {
			DataRow row = new DataRow();
			for (Object key : dataRow.keySet()) {
				row.put(key, dataRow.get(key));
			}
			table.add(row);
		}
		return table;
	}
}
