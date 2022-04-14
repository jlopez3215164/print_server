package com.pos.print.server.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.print.escposjava.PrinterService;
import com.pos.print.escposjava.print.NetworkPrinter;
import com.pos.print.escposjava.print.Printer;
import com.pos.print.server.entity.DeviceEntity;
import com.pos.print.server.entity.PrintDataEntity;
import com.pos.print.server.entity.PrinterInstructionEntity;
import com.pos.print.server.entity.TextInstructionEntity;
import com.pos.print.server.exception.XErrorFatalPrintSeverException;
import com.pos.print.server.exception.XErrorPrintServerConexion;
import com.pos.print.server.servelt.PoolConfigLoadServlet;

public class PrintFiscalThread extends Thread {
	private Logger log = LoggerFactory.getLogger(PrintFiscalThread.class);
	
	public PrintFiscalThread() {
			
	}

	@Override
	public void run() {
		try {
			while (true) {
				log.debug("Printer daemon Fiscal...");
				
				
				Thread.sleep(PoolConfigLoadServlet.THEARD_INTERVAL);
			}
		} catch (Throwable e) {
			log.error("Error: ", e);
		}
	}
}
