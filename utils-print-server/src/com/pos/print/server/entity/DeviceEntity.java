package com.pos.print.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceEntity {
	
	@JsonProperty("device") protected String device;
	//@JsonProperty("name") protected String name;
	//@JsonProperty("label") protected String label;
	//@JsonProperty("description") protected String description;
	@JsonProperty("ip") protected String ip;
	@JsonProperty("port") protected int port;
	//@JsonProperty("type") protected DeviceTypeEntity type;
	

	public DeviceEntity() {
		// TODO Auto-generated constructor stub
	}
	

	public DeviceEntity(String xDevice, String xLabel, String xIp, int xPort) {
		super();
		this.device = xDevice;
		//this.label = xLabel;
		this.ip = xIp;
		this.port = xPort;
	}

	public DeviceEntity(String xDevice, String xNameSystem, String xLabel,
			String xDescription, String xIp, int xPort, DeviceTypeEntity type) {
		super();
		this.device = xDevice;
		//this.name = xNameSystem;
		//this.label = xLabel;
		//this.description = xDescription;
		this.ip = xIp;
		this.port = xPort;
		//this.type = type;
	}

	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}


	


	


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	


	
	

}
