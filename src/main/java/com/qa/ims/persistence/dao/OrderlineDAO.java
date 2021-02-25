package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.utils.DBUtils;

public class OrderlineDAO implements Dao<Orderline> {
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Orderline modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long orderid = resultSet.getLong("orderid");
		Long itemid = resultSet.getLong("itemid");
		Long itemquant = resultSet.getLong("itemquant");
		
		return new Orderline (id, orderid, itemid, itemquant);
	}

	@Override
	public Orderline create(Orderline orderline) {
		try(Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orderline(`orderid`, `itemid`, `itemquant`) VALUES (?, ?, ?)");) {

				statement.setLong(1, orderline.getOrderid());

				statement.setLong(2, orderline.getItemid());

				statement.setLong(3, orderline.getItemquant());

				statement.executeUpdate();
				
				return readLatest();
			}
			catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}

			return null;
	}

	@Override
	public List<Orderline> readAll() {
		return null;
	}
	
	public Orderline readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orderid DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orderline read(Long orderid) {
	return null;
	}

	@Override
	public Orderline update(Orderline orderline) {
		try(Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orderline(orderid, itemid, itemquant) VALUES (?, ?, ?)");) {

				statement.setLong(1, orderline.getOrderid());

				statement.setLong(2, orderline.getItemid());

				statement.setLong(3, orderline.getItemquant());

				statement.executeUpdate();
				
				return readLatest();
			}
			catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}

			return null;
	}

	@Override
	public int delete(long orderid) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement( "DELETE FROM orderline WHERE orderid  = ?");) {
			statement.setLong(1, orderid);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0; 
		
	}

	

	
	}
	
