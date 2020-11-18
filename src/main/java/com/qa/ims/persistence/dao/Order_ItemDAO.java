package com.qa.ims.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class Order_ItemDAO {
	public static final Logger LOGGER = LogManager.getLogger();
	ItemDAO itemDAO = new ItemDAO();
	OrderDAO orderDAO = new OrderDAO();
	
	public Order readLatest() {
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Select orders.CustomerID, orders.OrderID from orders join order_item on order_item.OrderID = orders.OrderID order by order_item.OI_ID desc limit 1;");
				){
					resultSet.next();
					return orderDAO.limitedmodel(resultSet);
				} catch(SQLException e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return null;
	}
	
	public List<Item> readItemsByOrder(Long OrderID){
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Select items.title,items.price,items.ItemID from items join order_item on order_item.ItemID = items.ItemID where order_item.OrderID ="+OrderID+";");
				){
				List<Item> items = new ArrayList<>();
				while(resultSet.next()) {
					items.add(itemDAO.modelFromResultSet(resultSet));
				}
				return(items);
		}catch(SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	public Order create(Long orderID, Long ItemID) {
		try (
				Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				){
				statement.executeUpdate("insert into order_item (OrderID,ItemID) values("+orderID+","+ItemID+");");
		return readLatest();
		}
		catch(SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage()); 
		}
		return null;
	}
	public Order remove(Long orderID, Long ItemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
				statement.executeUpdate("delete from order_item where ItemID ="+ ItemID+" AND OrderID ="+orderID+" limit 1;");
				return readLatest();
		}catch(SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Order cust(Long orderID, Long custID) {
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
				statement.executeUpdate("update orders set CustomerID ="+ custID+" where OrderID ="+orderID+";");
				return orderDAO.readLatest();
		}catch(SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
