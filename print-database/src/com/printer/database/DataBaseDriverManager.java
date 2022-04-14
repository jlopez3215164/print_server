package com.printer.database;

import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

import com.printer.exception.XDatabaseConexionException;

public class DataBaseDriverManager {
	//private static Logger log = LoggerFactory.getLogger(DataBaseDriverManager.class);
	public static DataSource getDataSource(String jndi) throws XDatabaseConexionException {
		DataSource dataSource=null;
		try {
			dataSource = (DataSource) new InitialContext().lookup(new StringBuilder("java:comp/env/").append(jndi).toString());
		}	catch(NameNotFoundException e) {   
			//log.error("Error: ",e);
			throw new XDatabaseConexionException();
		}	catch(Throwable e) {
			//log.error("Error: ",e);	
			throw new XDatabaseConexionException();
		}
		return dataSource;
	}
}
