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
		Long order_id = resultSet.getLong("order_id");
		Long item_id = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		String item_name = resultSet.getString("item_name");
		Double item_cost = resultSet.getDouble("item_cost");
		Double total_cost = resultSet.getDouble("TotalCost");
		String item_desc = resultSet.getString("item_desc");
		return new Order(order_id, fname, sname, item_id, quantity, item_name, item_cost, item_desc, total_cost);
	}

	public Order modelFromResultSetBeforeJoin(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("cust_id");
		return new Order(order_id, customer_id);
	}

	/**
	 * Reads in either all orders or all orderitems.
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
		return null;
	}
	/**
	 * Reads in the latest order or orderitem. TRUE = order. FALSE = orderitem.
	 * @param item
	 * @return
	 */
	public Order readLatestItem(boolean item) {
		String query = item ? Queries.READLATESTORDER.getDescription() : Queries.READALLORDERITEMS.getDescription();
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			resultSet.next();
			if (item) {
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
			statement.setLong(1, order.getCustomer_id());
			statement.executeUpdate();

			return readLatestItem(false);

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates or updates an orderitem. TRUE = create. FALSE = update.
	 * @param order
	 * @param create
	 * @return
	 */
	public Order createUpdateItem(Order order, boolean create) {
		String query = create ? Queries.CREATEORDERITEM.getDescription() : Queries.UPDATEORDERITEM.getDescription();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setLong(1, order.getItem_id());
			statement.setLong(2, order.getOrder_id());
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
	public int delete(long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(Queries.DELETEORDER.getDescription());) {
			statement.setLong(1, order_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * Deletes an orderitem from the orderitem table.
	 * @param order_id
	 * @param item_id
	 * @return
	 */
	public int deleteOrderItem(long order_id, long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(Queries.DELETEORDERITEM.getDescription());) {
			statement.setLong(1, order_id);
			statement.setLong(2, item_id);
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
			sum += o.getTotal_cost();
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

//	public Order readItem(Long id) {
//	try (Connection connection = DBUtils.getInstance().getConnection();
//			PreparedStatement statement = connection
//					.prepareStatement(Queries.READORDERITEM.getDescription());) {
//		statement.setLong(1, id);
//		try (ResultSet resultSet = statement.executeQuery();) {
//			resultSet.next();
//			return modelFromResultSetBeforeJoin(resultSet);
//		}
//	} catch (Exception e) {
//		LOGGER.debug(e);
//		LOGGER.error(e.getMessage());
//	}
//
//	return null;
//
//}

//public Order readLatestItem() {
//try (Connection connection = DBUtils.getInstance().getConnection();
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement
//				.executeQuery(Queries.READLATESTORDERITEM.getDescription());) {
//	resultSet.next();
//	return modelFromResultSet(resultSet);
//} catch (Exception e) {
//	LOGGER.debug(e);
//	LOGGER.error(e.getMessage());
//}
//return null;
//}

//@Override
//public List<Order> readAll() {
//	try (Connection connection = DBUtils.getInstance().getConnection();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
//		List<Order> orders = new ArrayList<>();
//		while (resultSet.next()) {
//			orders.add(modelFromResultSetBeforeJoin(resultSet));
//		}
//
//		return orders;
//	} catch (SQLException e) {
//		LOGGER.debug(e);
//		LOGGER.error(e.getMessage());
//	}
//	return new ArrayList<>();
//}
//
//public List<Order> readAllItems() {
//	try (Connection connection = DBUtils.getInstance().getConnection();
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(
//					"SELECT c.first_name, c.surname, oi.order_id, oi.item_id, oi.quantity, i.item_name, i.item_cost, SUM(item_cost * quantity) as TotalCost, i.item_desc from orders_items oi left join items i on i.item_id=oi.item_id left join orders o on o.order_id=oi.order_id left join customers c on c.id=o.cust_id group by oi.order_item_id;");) {
//		List<Order> orderItems = new ArrayList<>();
//		while (resultSet.next()) {
//			orderItems.add(modelFromResultSet(resultSet));
//		}
//		return orderItems;
//	} catch (SQLException e) {
//		LOGGER.debug(e);
//		LOGGER.error(e.getMessage());
//	}
//	return new ArrayList<>();
//}

//	public Order update(Order order) {
//	try (Connection connection = DBUtils.getInstance().getConnection();
//			PreparedStatement statement = connection.prepareStatement(Queries.UPDATEORDER.getDescription());) {
//		statement.setLong(1, order.getCustomer_id());
//		statement.executeUpdate();
//		return read(order.getCustomer_id(), false);
//	} catch (Exception e) {
//		LOGGER.debug(e);
//		LOGGER.error(e.getMessage());
//	}
//	return null;
//}

//  	public Order updateItem(Order o) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection.prepareStatement(Queries.UPDATEORDERITEM.getDescription());) {
//			statement.setLong(1, o.getQuantity());
//			statement.setLong(2, o.getItem_id());
//			statement.setLong(3, o.getOrder_id());
//			statement.executeUpdate();
//			return read(o.getOrder_id());
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		return null;
//	}

//	public int deleteItem(long id) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection.prepareStatement(Queries.DELETEORDERITEM.getDescription());) {
//			statement.setLong(1, id);
//			return statement.executeUpdate();
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		return 0;
//	}

}