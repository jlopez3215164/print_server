package com.pos.print.server.entity;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class PrintDataEntity implements Serializable{
	
	@JsonProperty("device") private String device;
	@JsonProperty("code") private String code;
	@JsonProperty("comanda") private String comanda;
	@JsonProperty("file") private File file;
	@JsonProperty("instructions") private List<PrinterInstructionEntity> instructions;
	
	public PrintDataEntity() {
		// TODO Auto-generated constructor stub
	}

	public PrintDataEntity(String xDevice, String xCode, String xComanda) {
		super();
		this.device = xDevice;
		this.code = xCode;
		this.comanda = xComanda;
	}



	public PrintDataEntity(String xDevice, String xCode, String xComanda,
			File xFile) {
		super();
		this.device = xDevice;
		this.code = xCode;
		this.comanda = xComanda;
		this.file = xFile;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComanda() {
		return comanda;
	}

	public void setComanda(String comanda) {
		this.comanda = comanda;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<PrinterInstructionEntity> getInstructions() {
		return instructions;
	}

	@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.WRAPPER_ARRAY, property="instructions")
	public void setInstructions(List<PrinterInstructionEntity> instructions) {
		this.instructions = instructions;
	}

}
