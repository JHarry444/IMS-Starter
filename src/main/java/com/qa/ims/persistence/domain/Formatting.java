package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public enum Formatting {
	BR("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private String description;
	
	private Formatting(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

}
