package com.oracle.gdms.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GoodsType implements Serializable{

	private Integer gtid;
	private String name;
	private String description;

	public Integer getGtid() {
		return gtid;
	}

	public void setGtid(Integer gtid) {
		this.gtid = gtid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
