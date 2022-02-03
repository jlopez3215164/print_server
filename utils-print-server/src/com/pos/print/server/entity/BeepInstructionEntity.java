package com.pos.print.server.entity;

import com.pos.print.escposjava.PrinterService;

public class BeepInstructionEntity extends PrinterInstructionEntity{

	@Override
	public void sendPrint(PrinterService service) throws Throwable {
		service.beep(4,1);
	}
}
