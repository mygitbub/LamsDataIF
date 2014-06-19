package com.bwzk.pojo;

import java.io.Serializable;

public class SGroup implements Serializable {
	private static final long serialVersionUID = 1318932593055723177L;

	private Integer did;

    private Integer pid;

    private String qzh;

    private Integer gid;

    private String gname;

    private String bz;

    private String depcode;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getQzh() {
        return qzh;
    }

    public void setQzh(String qzh) {
        this.qzh = qzh == null ? null : qzh.trim();
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode == null ? null : depcode.trim();
    }

	@Override
	public String toString() {
		return this.getDid()+" : "+this.getPid()+" : " + this.getGname();
	}
    
    
}