package com.qa.ims.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

	private Statement statement;
	private Connection connect;

	public DBConnector() throws SQLException {
		// This is going to open our connection!
		// (jdbc:mysql:localhost;etc, root, root)
		connect = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
		this.statement = connect.createStatement();
		// statements.executeUpdate("sting of sql");
		// statements.executeQUery(sql);
	}

	// Create
	public void createPerson(String first, String last) throws SQLException {
		String sql = "INSERT INTO customers(`first_name`, `surname`) VALUES (?,?)";
		PreparedStatement ps = connect.prepareStatement(sql);
		ps.setString(1, first);
		ps.setString(2, last);
		ps.execute();
	}

	// Read all
	public void readPeople() throws SQLException {
		String sql = "SELECT * FROM customers";
		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			System.out.println(String.format("ID: %d, first_name: %s, surname: %s", rs.getInt("id"),
					rs.getString("first_name"), rs.getString("surname")));

		}
	}

	// Update
	public void updatePerson(int id, String fname, String lname) throws SQLException {
		String sql = String.format("UPDATE customers SET `first_name`= '%s', `surname`='%s' WHERE id='%d';", fname,
				lname, id);
		statement.executeUpdate(sql);
	}

	// Delete
	public void deletePerson(int id) throws SQLException {
		String sql = String.format("DELETE FROM customers where id = '%d';", id);
		statement.executeUpdate(sql);
	}

	// Read One
	public void readOne(int id) throws SQLException {
		String sql = "SELECT * from customers WHERE id=" + id;
		ResultSet rs = statement.executeQuery(sql);

		if (!rs.next()) {
			System.out.println("No matches found");
		} else {
			do {
				System.out.println(rs.getString("first_name") + " " + rs.getString("surname"));
			} while (rs.next());

		}
	}

	public void tearDown() throws SQLException {
		connect.close();

	}
}