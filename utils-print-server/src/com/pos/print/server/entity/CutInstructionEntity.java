package com.pos.print.server.entity;

import com.pos.print.escposjava.PrinterService;

public class CutInstructionEntity  extends PrinterInstructionEntity {
	
	@Override
	public void sendPrint(PrinterService service) throws Throwable {
		service.cutFull();
	}
}
