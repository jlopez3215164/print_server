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

public class PrintThread extends Thread {
	private Logger log = LoggerFactory.getLogger(PrintThread.class);
	private DeviceEntity printer = null;
	private String urlPool;
	private StringBuilder urlPoolDevice;

	public PrintThread(String name, DeviceEntity printer, String urlPool, StringBuilder urlPoolDevice) {
		super(name);
		this.printer = printer;
		this.urlPool = urlPool;
		this.urlPoolDevice = urlPoolDevice;
	}

	@Override
	public void run() {
		String lastFilePrint = "";
		try {
			while (true) {
				// log.debug("Printer daemon...");
				File[] file = null;
				try {
					file = dirList(urlPool, printer.getDevice(), ".printer");
					if (file != null && file.length > 0) {
						for (int i = 0; i < file.length; i++) {
							if (!file[i].getName().equals(lastFilePrint)) {
								log.debug("Imprimio");
								log.debug(printer.getIp());
								lastFilePrint = file[i].getName();// AJUSTE
								print(printer.getIp(), printer.getPort(), PoolConfigLoadServlet.SOCKET_TIMEOUT,
										file[i]);
								if (PoolConfigLoadServlet.THEARD_INTERVAL_LIST > 0)
									Thread.sleep(PoolConfigLoadServlet.THEARD_INTERVAL_LIST);
							} else {
								log.info("Borrado!!");
								file[i].delete();
							}
						}
					}
				} catch (Throwable e) {
					log.error("Error Job Printer: ", e);
					lastFilePrint = "";
				} finally {
					file = null;
				}
				Thread.sleep(PoolConfigLoadServlet.THEARD_INTERVAL);
			}
		} catch (Throwable e) {
			log.error("Error: ", e);
		}
	}

	public void print(String ip, int port, int timeOut, File file) throws XErrorFatalPrintSeverException {
		Socket mSocket = new Socket();
		PrintDataEntity data = null;
		StringBuilder urlPoolDeviceTmp = null;
		PrinterService service = null;
		Printer printer = null;
		try {
			// data = new ObjectMapper().readValue(file, PrintDataEntity.class);
			log.debug("TIMEOUT --> " + timeOut);
			log.debug("PORT --> " + port);
			log.debug("IP --> " + ip);

			printer = new NetworkPrinter(ip, 9100);
			service = new PrinterService(printer);

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					log.debug(line);
					// PrinterInstructionEntity comand = new TextInstructionEntity();
					// comand.setText(line);
					// comand.sendPrint(service);
					service.printLn(line);
					// service.lineBreak();
					log.debug("Envio");
				}
				service.cutFull();
			}
			// ----------------------------------
			/*
			 * if (data.getInstructions() != null) { for (PrinterInstructionEntity instruc :
			 * data.getInstructions()) { instruc.sendPrint(service); } }
			 */
			// ------------------------------------------
			if (PoolConfigLoadServlet.BACKUP) {
				urlPoolDeviceTmp = new StringBuilder(urlPoolDevice).append(PoolConfigLoadServlet.FILE_SEPARATOR)
						.append(file.getName());
				try {
					Files.move(file.toPath(), new File(urlPoolDeviceTmp.toString()).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (Throwable er) {
					log.error("ERROR MOVE: " + er);
				}
			} else {
				boolean isDelete = file.delete();
				if (!isDelete) {
					log.debug("Borrado --> " + file.delete());
					log.debug("Error borrando archivo --> " + file.toPath());
				}
			}

			if (file.exists()) {
				log.debug("Renombrando archivo --> " + file.toPath());
				file.renameTo(new File(file.getAbsolutePath() + ".xError"));
			}
		} catch (UnknownHostException e) {
			throw new XErrorPrintServerConexion(e);
		} catch (IOException e) {
			throw new XErrorPrintServerConexion(e);
		} catch (Throwable e) {
		} finally {
			try {
				mSocket.close();
			} catch (IOException e) {
			}
			mSocket = null;
			data = null;
			urlPoolDeviceTmp = null;
			if (service != null)
				service.close();
			if (printer != null)
				printer.close();
		}
	}

	@SuppressWarnings("unchecked")
	private File[] dirList(String url, final String preFijo, final String ext) {
		File files[] = new File(url).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toUpperCase().startsWith(preFijo.toUpperCase())
						&& name.toUpperCase().endsWith(ext.toUpperCase());
			}
		});
		if (files != null) {
			Arrays.sort(files, new Comparator() {
				public int compare(final Object o1, final Object o2) {
					return new Long(((File) o1).lastModified()).compareTo(new Long(((File) o2).lastModified()));
				}
			});
		}
		return files;
	}
}
