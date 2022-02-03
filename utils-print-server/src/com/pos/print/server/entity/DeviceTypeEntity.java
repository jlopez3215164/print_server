package com.pos.print.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceTypeEntity {
	@JsonProperty("id") private int id;
	@JsonProperty("desc") private String desc;
	
	public DeviceTypeEntity() {
		// TODO Auto-generated constructor stub
	}

	
	public DeviceTypeEntity(int id) {
		super();
		this.id = id;
	}


	public DeviceTypeEntity(int id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
