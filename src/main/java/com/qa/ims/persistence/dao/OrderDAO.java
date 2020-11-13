package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("OrderID");
		Long customerID = resultSet.getLong("CustomerID");
		String customer = resultSet.getString("Customer");
		String items = resultSet.getString("Items");
		Long total = resultSet.getLong("Total");
		return new Order(id, customerID,customer,items,total);
	}
	
	public Order limitedmodel(ResultSet resultSet) throws SQLException{
		Long id = resultSet.getLong("OrderID");
		Long customerID = resultSet.getLong("CustomerID");
		return new Order(id,customerID);
	}

	//reading orders
	public List<Order> readAll(){
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select orders.OrderID, customers.CustomerID , concat(customers.first_name,\" \", customers.surname) as Customer, group_concat(items.title separator\", \") as Items, sum(items.price) as Total from order_item  join items on order_item.ItemID = items.ItemID  join orders on order_item.OrderID = orders.OrderID join customers on orders.CustomerID = customers.CustomerID group by OrderID order by OrderID;;"
						);
				){
				List<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(modelFromResultSet(resultSet));
				}
				return orders;
				
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();
	}
	
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery("Select * from orders order by OrderID desc limit 1");
		){
			resultSet.next();
			return limitedmodel(resultSet);
		} catch(Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}

	@Override
	public Order create(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
				statement.executeUpdate("insert into orders (CustomerID) values("+t.getCustomerID()+");");
				return readLatest();
		}catch(Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
				statement.executeUpdate("delete from order_item where OrderID ="+id+";");
			return statement.executeUpdate("delete from orders where OrderID ="+id+";");
		}catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
