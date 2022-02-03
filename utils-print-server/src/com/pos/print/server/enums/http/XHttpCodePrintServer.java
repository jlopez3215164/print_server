package com.pos.print.server.enums.http;


public enum XHttpCodePrintServer {
	xErrorFileFormatPrintServer(591), 
	XErrorFatalParamsPrintSever(590);
	
	private int code;
	private XHttpCodePrintServer(int code) {
		this.code = code;
	}
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

}
