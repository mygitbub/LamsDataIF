package com.bwzk.pojo;

import java.io.Serializable;

import com.bwzk.pojo.MLDepNewDataSet.Table;

/**
 * 为了方便遍历 ml的 dep对象加一个全宗号
 * @author wwwly
 */
public class MlLams implements Serializable{
	private static final long serialVersionUID = -775328215049201198L;
	public MlLams(Table mlObj, String qzh) {
		this.mlObj = mlObj;
		this.qzh = qzh;
	}
	private MLDepNewDataSet.Table mlObj;
	private String qzh;
	public MLDepNewDataSet.Table getMlObj() {
		return mlObj;
	}
	public void setMlObj(MLDepNewDataSet.Table mlObj) {
		this.mlObj = mlObj;
	}
	public String getQzh() {
		return qzh;
	}
	public void setQzh(String qzh) {
		this.qzh = qzh;
	}
	
	
}
