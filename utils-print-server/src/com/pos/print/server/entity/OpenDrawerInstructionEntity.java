package com.pos.print.server.entity;

import com.pos.print.escposjava.PrinterService;

public class OpenDrawerInstructionEntity extends PrinterInstructionEntity{

	@Override
	public void sendPrint(PrinterService service) throws Throwable {
		service.openCashDrawerPin2();
		service.openCashDrawerPin5();
	}

}
