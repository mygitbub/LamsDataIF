package com.bwzk.pojo;

import java.io.Serializable;

import com.bwzk.util.GlobalFinalAttr;

/**
 * <p>  Title: GepsMappingBean 描述代码表 </p>
 * <p> 梦龙的geps和档案系统映射的mapping </p>
 * 
 * @author luyuwww
 * @date 2014年1月20日
 */
public class GepsMappingBean implements Serializable {

	public String getPrjTableName() {
		return GlobalFinalAttr.EOperLevelType.D_PRJ_LEVEL.getValueStr() + libcode;
	}

	public String getVolTableName() {
		return GlobalFinalAttr.EOperLevelType.D_VOL_LEVEL.getValueStr() + libcode;
	}

	public String getDfileTableName() {
		return GlobalFinalAttr.EOperLevelType.D_FILE_LEVEL.getValueStr() + libcode;
	}

	public String getEfileTableName() {
		return GlobalFinalAttr.EOperLevelType.E_EFILE_LEVEL.getValueStr() + libcode;
	}

	private Integer libcode;
	private String prjSql;
	private String volSql;
	private String dFileSql;
	private String eFileSql;

	public Integer getLibcode() {
		return libcode;
	}

	public void setLibcode(Integer libcode) {
		this.libcode = libcode;
	}

	public String getPrjSql() {
		return prjSql;
	}

	public void setPrjSql(String prjSql) {
		this.prjSql = prjSql;
	}

	public String getVolSql() {
		return volSql;
	}

	public void setVolSql(String volSql) {
		this.volSql = volSql;
	}

	public String getdFileSql() {
		return dFileSql;
	}

	public void setdFileSql(String dFileSql) {
		this.dFileSql = dFileSql;
	}

	public String geteFileSql() {
		return eFileSql;
	}

	public void seteFileSql(String eFileSql) {
		this.eFileSql = eFileSql;
	}
	private static final long serialVersionUID = 2219337505528263682L;
}
