package com.pos.print.server.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigPrintersEntity {
	@JsonProperty("printers")	private List<DeviceEntity> printers;
	
	public ConfigPrintersEntity() {
		// TODO Auto-generated constructor stub
	}

	public List<DeviceEntity> getPrinters() {
		return printers;
	}

	public void setPrinters(List<DeviceEntity> printers) {
		this.printers = printers;
	}

	
}
