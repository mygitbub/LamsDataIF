package com.bwzk.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * 梦龙与档案的字段对应关系 对于的档案的代码表
 */
public class MLFieldMapping implements Serializable {
	private String enName;
	private String chName;
	private String dTableName;//对应广梦的文件类型的d表tablename
	private String fileType;//广梦的文件类型
	private Map<String , String> fieldMap;

	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getdTableName() {
		return dTableName;
	}
	public void setdTableName(String dTableName) {
		this.dTableName = dTableName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Map<String, String> getFieldMap() {
		return fieldMap;
	}
	public void setFieldMap(Map<String, String> fieldMap) {
		this.fieldMap = fieldMap;
	}
	private static final long serialVersionUID = -4479346184571369145L;
}
