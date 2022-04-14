package com.pos.print.server.threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pos.print.server.servelt.PoolConfigLoadServlet;
import com.printer.database.DriverManagerMail;
import com.printer.exception.XDatabaseConexionException;

public class PrintFiscalThread extends Thread {
	private Logger log = LoggerFactory.getLogger(PrintFiscalThread.class);

	public PrintFiscalThread() {

	}

	@Override
	public void run() {
		try {
			while (true) {
				log.debug("Printer daemon Fiscal...");
				
				Connection con = null;
				PreparedStatement pstm = null;
				ResultSet rs = null;
				try{
					con = DriverManagerMail.getDataSource().getConnection();
					pstm = con.prepareStatement("select * from customer_order where is_print_fiscal = 1");
		        	rs = pstm.executeQuery();
		        	while(rs.next()) {
		        		log.debug("Imprimiendo la factura...");
		        		pstm = con.prepareStatement("update customer_order set is_print_fiscal = 2 where order_id = ?");
		        		pstm.setInt(1, rs.getInt("order_id"));
			        	pstm.execute();	
		        	}
					
				}catch (XDatabaseConexionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        catch (Exception e)
		        {
		            e.printStackTrace();
		        }finally{
					try {
						if(con != null)
							con.close();
						if(pstm != null)
							pstm.close();
						if(rs != null)
							rs.close();
		            } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
		        }
				Thread.sleep(PoolConfigLoadServlet.THEARD_INTERVAL);
			}
		} catch (Throwable e) {
			log.error("Error: ", e);
		}
	}
}
