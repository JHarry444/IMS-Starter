package com.qa.ims.exceptions;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionController {
    
	public static final Logger LOGGER = LogManager.getLogger();

    public static SQLException returnException(SQLException exp) {
        if (exp.getSQLState().equals("S1000")) {
            return ItemNotInOrderException;
        } else {
            LOGGER.info(exp.getSQLState());
        }
    }

}
