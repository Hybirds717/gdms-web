package com.oracle.gdms.entity;

import java.io.Serializable;

public class AreaModel implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4416184153806104678L;
	private int areaid;
	private String name;
	private String type;
	private int parentid;

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
