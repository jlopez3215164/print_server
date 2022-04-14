package com.printer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.printer.exception.XDatabaseConexionException;

public class DriverManagerMail {
	// private static final Logger log=
	// LoggerFactory.getLogger(DriverManagerMail.class);

	public static DataSource getDataSource() throws XDatabaseConexionException {
		try {
			return DataBaseDriverManager.getDataSource("jdbc/ServerSQL");
		} catch (XDatabaseConexionException e) {
			// TODO Auto-generated catch block
			// log.error("Error: ",e);
			throw new XDatabaseConexionException();
		}
	}

	public static Connection getDataSourceSqlLite(String url) throws XDatabaseConexionException {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:" + url);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			// log.error("Error: ",e);
			throw new XDatabaseConexionException();
		}
	}

}
