package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAO implements Dao<OrderItems> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderItems modelFromResultSet(ResultSet resultSet) throws SQLException {
		String fname = resultSet.getString("first_name");
		String sname = resultSet.getString("surname");
		Long order_id = resultSet.getLong("order_id");
		Long item_id = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		String item_name = resultSet.getString("item_name");
		Double item_cost = resultSet.getDouble("item_cost");
		Double total_cost = resultSet.getDouble("TotalCost");
		String item_desc = resultSet.getString("item_desc");
		return new OrderItems(fname, sname, order_id, item_id, quantity, item_name, item_cost, total_cost, item_desc);
	}

	public OrderItems modelFromResultSetBeforeJoin(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		Long item_id = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("Quantity");
		return new OrderItems(order_id, item_id, quantity);
	}

	@Override
	public List<OrderItems> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT c.first_name, c.surname, oi.order_id, oi.item_id, oi.quantity, i.item_name, i.item_cost, SUM(item_cost * quantity) as TotalCost, i.item_desc from orders_items oi left join items i on i.item_id=oi.item_id left join orders o on o.order_id=oi.order_id left join customers c on c.id=o.cust_id group by oi.order_item_id;");) {
			List<OrderItems> orderItems = new ArrayList<>();
			while (resultSet.next()) {
				orderItems.add(modelFromResultSet(resultSet));
			}
			return orderItems;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrderItems readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orders_items ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSetBeforeJoin(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<OrderItems> readSpecific(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT c.first_name, c.surname, oi.order_id, oi.item_id, oi.quantity, i.item_name, i.item_cost, SUM(item_cost * quantity) as TotalCost" + 
						", i.item_desc from orders_items oi left join items i on i.item_id=oi.item_id left join orders o on o.order_id=oi.order_id left join customers" 
								+" c on c.id=o.cust_id group by oi.order_item_id HAVING oi.order_id = ?");) {
			statement.setLong(1, id);
			List<OrderItems> orderItems = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					orderItems.add(modelFromResultSet(resultSet));
				}
				return orderItems;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems create(OrderItems orderItems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders_items(item_id, order_id, quantity) VALUES (?, ?, ?) ");) {
			statement.setLong(1, orderItems.getItem_id());
			statement.setLong(2, orderItems.getOrder_id());
			statement.setLong(3, orderItems.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM orders_items WHERE order_id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSetBeforeJoin(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		return null; 

	}

	@Override
	public OrderItems update(OrderItems oi) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders_items SET item_id = ?, quantity = ? WHERE order_id = ?");) {
			statement.setLong(1, oi.getItem_id());
			statement.setLong(2, oi.getQuantity());
			statement.setLong(3, oi.getOrder_id());
			statement.executeUpdate();
			return read(oi.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE order_items_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public int deleteNullOrders() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("DELETE o from orders o\r\n"
								+ "left join orders_items oi on o.order_id=oi.order_id where quantity is null;");) {
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public Double calculateCost(List<OrderItems> oi) {
		Double sum = 0.0;
		for (OrderItems o : oi) {
			sum += o.getTotal_cost();
		}
		return sum;
	}

}
