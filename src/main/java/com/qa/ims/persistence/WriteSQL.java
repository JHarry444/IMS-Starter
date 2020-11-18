package com.qa.ims.persistence;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WriteSQL {
	public static final Logger LOGGER  = LogManager.getLogger();
	GenerateSQL sqlgen = new GenerateSQL();
	
	public String SQLText() {
		try {
			FileWriter write = new FileWriter("src/main/resources/sql-data.sql");
			for(String string:sqlgen.GenerateCustomers()) {
				write.write(string);
			}
			for(String string:sqlgen.GenerateItems()) {
				write.write(string);
			}
			for(String string:sqlgen.GenerateOrders()) {
				write.write(string);
			}
			for(String string:sqlgen.GenerateOrder_Item()) {
				write.write(string);
			}
			write.close();
		}catch(IOException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return( "yote");
	}
}
