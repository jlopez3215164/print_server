package com.pos.print.server.entity;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pos.print.escposjava.PrinterService;

public class TextInstructionEntity extends PrinterInstructionEntity{

	@JsonProperty("text") private String text;
	
	public TextInstructionEntity() {
		
	}
	
	public TextInstructionEntity(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void sendPrint(PrinterService service) throws Throwable {
		service.print(StringEscapeUtils.unescapeJava(this.text));
	}

}
