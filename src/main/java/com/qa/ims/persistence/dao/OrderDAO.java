package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.ims.controller.Queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		String fname = resultSet.getString("first_name");
		String sname = resultSet.getString("surname");
		Long orderId = resultSet.getLong("order_id");
		Long itemId = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		String itemName = resultSet.getString("item_name");
		Double itemCost = resultSet.getDouble("item_cost");
		Double totalCost = resultSet.getDouble("TotalCost");
		String itemDesc = resultSet.getString("item_desc");
		return new Order(orderId, fname, sname, itemId, quantity, itemName, itemCost, itemDesc, totalCost);
	}

	public Order modelFromResultSetBeforeJoin(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");
		Long customerId = resultSet.getLong("cust_id");
		return new Order(orderId, customerId);
	}

	/**
	 * Reads in either all orders or all orderItems.
	 * @param allItems
	 * @return
	 */
	public List<Order> readAllItems(boolean allItems) {
		String query = null;
		if (allItems) {
			query = Queries.READALLORDERITEMS.getDescription();
		} else {
			query = Queries.READALL.specifyTable("orders");
		}
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				if (allItems) {
					orders.add(modelFromResultSet(resultSet));
				} else {
					orders.add(modelFromResultSetBeforeJoin(resultSet));
				}
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	/**
	 * Reads a specific row in from the joined table.
	 * @param id
	 * @return
	 */
	public List<Order> readSpecific(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Queries.READSPECIFICORDERITEM.getDescription());) {
			statement.setLong(1, id);
			List<Order> order = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					order.add(modelFromResultSet(resultSet));
				}
				return order;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	/**
	 * Reads in the latest order or orderItem. TRUE = order. FALSE = orderItem.
	 * @param item
	 * @return
	 */
	public Order readLatestItem(boolean order) {
		String query = order ? Queries.READLATESTORDER.getDescription() : Queries.READALLORDERITEMS.getDescription();
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			resultSet.next();
			if (order) {
				return modelFromResultSetBeforeJoin(resultSet);
			} else {
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} 
		return null;
	}
	/**
	 * Reads in one row from order table.
	 * @param id
	 * @param item
	 * @return
	 */
	public Order read(Long id, boolean item) {
		String query = item ? Queries.READORDER.getDescription() : Queries.READORDERITEM.getDescription();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				if (item) {
					return modelFromResultSet(resultSet);
				} else {
					return modelFromResultSetBeforeJoin(resultSet);
				}
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());

		}
		return null;
	}
	
	/**
	 * Creates or updates an order. TRUE = create. FALSE = update.
	 * @param order
	 * @param create
	 * @return
	 */ 
	public Order createUpdateOrder(Order order, boolean create) {
		String query = create ? Queries.CREATEORDER.getDescription() : Queries.UPDATEORDER.getDescription();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setLong(1, order.getCustomerId());
			statement.executeUpdate();

			return readLatestItem(true);

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	} 

	/**
	 * Creates or updates an orderItem. TRUE = create. FALSE = update.
	 * @param order
	 * @param create
	 * @return
	 */
	public Order createUpdateOrderItem(Order order, boolean create) {
		String query = create ? Queries.CREATEORDERITEM.getDescription() : Queries.UPDATEORDERITEM.getDescription();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setLong(1, order.getItemId());
			statement.setLong(2, order.getOrderId());
			statement.setLong(3, order.getQuantity());
			statement.executeUpdate();
			return readLatestItem(false);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	 
	/**
	 * Deletes an order from the order table.
	 * @param id
	 */
	@Override
	public int delete(long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(Queries.DELETEORDER.getDescription());) {
			statement.setLong(1, orderId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} 
		return 0;
	}

	/**
	 * Deletes an orderItem from the orderItem table.
	 * @param orderId
	 * @param itemId
	 * @return
	 */
	public int deleteOrderItem(long orderId, long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(Queries.DELETEORDERITEM.getDescription());) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	/**
	 * Deletes null orders - orders without any items.
	 * @return
	 */
	public int deleteNullOrders() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(Queries.DELETENULLORDERS.getDescription());) {
			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 
	 * @param oi
	 * @return
	 */
	public Double calculateCost(List<Order> oi) {
		Double sum = 0.0;
		for (Order o : oi) {
			sum += o.getTotalCost();
		}
		return sum;
	}

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

}