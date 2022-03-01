package com.pos.print.server.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pos.print.escposjava.PrinterService;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public abstract class PrinterInstructionEntity implements Serializable{
	
	@JsonProperty("text") private String text;
	@JsonProperty("base64Image") private String base64Image;
	@JsonProperty("pixelWidth") private int pixelWidth;
	
	public abstract void sendPrint(PrinterService service) throws Throwable;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	public int getPixelWidth() {
		return pixelWidth;
	}
	
	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}
	
}
