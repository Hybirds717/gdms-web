package com.oracle.gdms.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseEntity {

	private int code;
	private String message;
	private String data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
