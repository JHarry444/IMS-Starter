package com.qa.ims.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manages Database configuration and connections.
 * 
 * @author jh
 * 
 */
public class DBUtils {

	private static final Logger LOGGER = LogManager.getLogger();

	private final String DB_URL;

	private final String DB_USER;

	private final String DB_PASS;

	/**
	 * Initialises the the <code>DB_URL</code>, <code>DB_USER</code> and
	 * <code>DB_PASS</code> from the designated properties file.
	 * 
	 * @param propertiesFileName name of the properties file in the
	 *                           <strong>resources</strong> folder
	 */
	private DBUtils(String propertiesFileName) {
		Properties dbProps = new Properties();
		try (InputStream fis = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
			dbProps.load(fis);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		this.DB_URL = dbProps.getProperty("db.url", "");
		this.DB_USER = dbProps.getProperty("db.user", "");
		this.DB_PASS = dbProps.getProperty("db.password", "");
	}

	/**
	 * Calls the <code>DBUtils(String propertiesFileName)</code> constructor with
	 * the default property file name <strong>db.properties</strong>.
	 */
	public DBUtils() {
		this("db.properties");
	}

	/**
	 * Accepts an array of paths to SQL files which are then executed using the
	 * <code>exectueSQLFile</code> method.
	 * 
	 * @param paths Paths to SQL files as a <code>String</code> array
	 * @return Number of rows in the database modified by the executed queries
	 */
	public int executeSQLFiles(String... paths) {
		int modified = 0;

		for (String path : paths) {
			modified += executeSQLFile(path);
		}

		return modified;
	}

	/**
	 * Reads in an SQL file, separates the contents into separate queries and then
	 * executes them individually.
	 * 
	 * @param filePath Path to an SQL file
	 * @return Number of rows in the database modified by the executed queries
	 */
	public int executeSQLFile(String filePath) {
		int modified = 0;
		try (Connection connection = this.getConnection();
				BufferedReader br = new BufferedReader(new FileReader(filePath));) {
			String fileAsString = br.lines().reduce((acc, next) -> acc + next).orElse("");
			String[] queries = fileAsString.split(";");
			modified += Stream.of(queries).map(string -> {
				try (Statement statement = connection.createStatement();) {
					return statement.executeUpdate(string);
				} catch (Exception e) {
					LOGGER.debug(e);
					return 0;
				}
			}).reduce((acc, next) -> acc + next).orElse(0);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return modified;
	}

	/**
	 * Uses <code>DriverManager</code> to establish a connection to the database
	 * using the <code>DB_URL</code>, <code>DB_USER</code> and <code>DB_PASS</code>.
	 * 
	 * @return A database connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

	public static DBUtils instance;

	/**
	 * Gets the singleton instance of <code>DBUtils</code>.
	 * 
	 * @return singleton instance of <code>DBUtils</code>.
	 */
	public static DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}

}
