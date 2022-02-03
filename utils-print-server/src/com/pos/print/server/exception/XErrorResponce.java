package com.pos.print.server.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class XErrorResponce {
	@JsonProperty("code") private int code;
	@JsonProperty("msg") private String msg;
	@JsonProperty("description") private String description;
	
	public XErrorResponce() {
		// TODO Auto-generated constructor stub
	}
	
	

	public XErrorResponce(int code) {
		super();
		this.code = code;
	}



	public XErrorResponce(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}



	public XErrorResponce(int code, String msg, String description) {
		super();
		this.code = code;
		this.msg = msg;
		this.description = description;
	}



	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
