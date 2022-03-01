package com.pos.print.server.servelt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.print.server.entity.ConfigPrintersEntity;
import com.pos.print.server.entity.DeviceEntity;
import com.pos.print.server.threads.PrintThread;

/**
 * Servlet implementation class PoolServlet
 */

@WebServlet(name = "PoolConfigLoadServlet", urlPatterns = "/pool/config", loadOnStartup = 0)
public class PoolConfigLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(PoolConfigLoadServlet.class);
	public static String REAL_PATH, REAL_PATH_POOL, REAL_PATH_PRINTERS_CONFIG,
			FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
	public static String REAL_PATH_CONFIG;
	public static int SOCKET_TIMEOUT, THEARD_INTERVAL, THEARD_INTERVAL_LIST;
	public static boolean BACKUP = false;
	public static String PRINT_SERVER_VERSION;
	public static ConfigPrintersEntity conf;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		REAL_PATH = config.getServletContext().getRealPath("/");

		log.info("***** Print Server Root Files Configs --> " + REAL_PATH);
		Properties confPro = loadProperties();

		if (confPro != null) {
			if (!confPro.getProperty("print.server.enable", "false").equalsIgnoreCase("true")) {
				log.warn("Print Server Pool Theard Printer is Disable..!");
			} else {
				REAL_PATH_POOL = new StringBuilder(REAL_PATH).append(FILE_SEPARATOR)
						.append(confPro.getProperty("print.server.folder", "Queue")).toString();
				log.info("***** Print Server Root Files Pool --> {}", REAL_PATH_POOL);
				File ftmp = new File(REAL_PATH_POOL);
				ftmp.mkdirs();
				log.info("***** Print Server Root Files Queue Exists Directory --> {}", ftmp.exists());

				SOCKET_TIMEOUT = Integer.parseInt(confPro.getProperty("print.server.socket.timeOut", "1000"));
				log.info("***** Print Server Queue Theard Printer Socket TimeOut --> {} millisecond", SOCKET_TIMEOUT);

				THEARD_INTERVAL = Integer.parseInt(confPro.getProperty("print.server.theard.time.interval", "300"));
				log.info("***** Print Server Queue Theard Printer Theard Interval --> {} millisecond", THEARD_INTERVAL);

				THEARD_INTERVAL_LIST = Integer
						.parseInt(confPro.getProperty("print.server.theard.time.interval.list", "0"));
				log.info("***** Print Server Queue Theard Printer Theard Interval List --> {} millisecond",
						THEARD_INTERVAL_LIST);

				BACKUP = confPro.getProperty("print.server.backup", "false").equalsIgnoreCase("true");
				log.info("***** Print Server Queue Backup --> {}", BACKUP);

				REAL_PATH_PRINTERS_CONFIG = new StringBuilder(REAL_PATH).append(FILE_SEPARATOR).append("WEB-INF")
						.append(FILE_SEPARATOR)
						.append(confPro.getProperty("print.server.file.config.priters", "printers.config")).toString();
				log.info("***** Print Server Root Config Printers --> {}", REAL_PATH_PRINTERS_CONFIG);
				conf = null;
				try {
					conf = new ObjectMapper().readValue(new File(REAL_PATH_PRINTERS_CONFIG),
							ConfigPrintersEntity.class);
					for (DeviceEntity dev : conf.getPrinters()) {
						StringBuilder urlPoolDevice = new StringBuilder(REAL_PATH_POOL).append(FILE_SEPARATOR)
								.append(dev.getDevice());
						PrintThread hilo = new PrintThread(dev.getDevice(), dev, REAL_PATH_POOL, urlPoolDevice);
						hilo.start();

						ftmp = new File(urlPoolDevice.toString());
						ftmp.mkdirs();

						log.info("***** Print Server Queue Theard Printer --> {} is Ok..", dev.getDevice());
						log.info("***** Print Server Root Files Queue {} Exists Directory --> {}", dev.getDevice(),
						ftmp.exists());
					}

					log.info("***** Print Server Load All is Ok..!");
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					log.error("***** Error CRITICO Format Config Printers", e);
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					log.error("***** Error CRITICO Format Config Printers", e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.error("***** Error CRITICO Format Config Printers", e);
				}
			}
		}
		loadInfo();
	}

	private Properties loadProperties() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream(new StringBuilder(REAL_PATH).append(FILE_SEPARATOR).append("WEB-INF")
					.append(FILE_SEPARATOR).append("config_printers.properties").toString()));
			log.info("***** Load config.config is Ok..!");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error("***** Error CRITICO Load config.properties", e);
			return null;
		}
		return conf;
	}

	private Properties loadInfo() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream(new StringBuilder(REAL_PATH).append(FILE_SEPARATOR).append("WEB-INF")
					.append(FILE_SEPARATOR).append("info.properties").toString()));
			PRINT_SERVER_VERSION = conf.getProperty("version", "0");
			log.info("***** Load info.properties is Ok..!");
			log.info("**** Version: --> {}", PRINT_SERVER_VERSION);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error("***** Error CRITICO Load config.properties", e);
			return null;
		}
		return conf;
	}

}
