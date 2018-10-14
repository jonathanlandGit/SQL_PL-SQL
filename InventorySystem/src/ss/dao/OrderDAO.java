package ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ss.Order;
import ss.OrderItem;
import ss.util.DateUtil;

public class OrderDAO {
	
	public ArrayList<Order> getOrders(Date fromDate, Date toDate) {
		ArrayList<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("SELECT o.OrderNo, o.OrderDate, c.FName||' '||c.LName CustomerName, o.ShipDate, SUM(oi.quantity*oi.price) TotalAmount"
					+ " FROM ORDERS o JOIN ORDER_ITEMS oi ON o.OrderNo=oi.OrderNo"
					+ " JOIN CUSTOMERS c ON o.CustomerNo=c.CustomerNo"
					+ " WHERE o.OrderDate BETWEEN ? AND ? AND o.CancelDate IS NULL"
					+ " GROUP BY o.OrderNo, o.OrderDate, c.FName||' '||c.LName, o.ShipDate"
					+ " ORDER BY o.OrderNo");
			
			stmt.setDate(1, DateUtil.toSqlDate(fromDate));
			stmt.setDate(2, DateUtil.toSqlDate(toDate));
			rs = stmt.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setOrderNo(rs.getLong("OrderNo"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setCustomerName(rs.getString("CustomerName"));
				order.setShipDate(rs.getTimestamp("ShipDate"));
				order.setTotalAmount(rs.getDouble("TotalAmount"));
				orders.add(order);
			}
		} catch (Exception e){
			throw new RuntimeException("Error in getting orders.", e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orders;
	}
	
	public Order getOrder(long orderNo) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("SELECT OrderNo, OrderDate, FName||' '||LName CustomerName, ShipDate, CancelDate FROM ORDERS o JOIN CUSTOMERS c ON o.customerNo=c.customerNo WHERE OrderNo = ?");
			stmt.setLong(1, orderNo);
			rs = stmt.executeQuery();
			if(rs.next()){
				// Get Order details
				Order order = new Order();
				order.setOrderNo(rs.getLong("OrderNo"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setCustomerName(rs.getString("CustomerName"));
				order.setShipDate(rs.getTimestamp("ShipDate"));
				order.setCancelDate(rs.getTimestamp("CancelDate"));
				
				// Get Order items
				ArrayList<OrderItem> orderItems = getOrderItems(con, orderNo);
				order.setOrderItems(orderItems);
				
				return order;
			} else
				return null;
		} catch (Exception e){
			throw new RuntimeException("Error in getting order for no - " + orderNo, e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private ArrayList<OrderItem> getOrderItems(Connection con, long orderNo) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT OrderNo, Description, Quantity, Price"
					+ " FROM ORDER_ITEMS oi JOIN PRODUCTS p ON oi.productId=p.productId"
					+ " WHERE OrderNo = ?");
			
			stmt.setLong(1, orderNo);
			rs = stmt.executeQuery();
			ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
			while(rs.next()){
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderNo(rs.getLong("OrderNo"));
				orderItem.setProductDesc(rs.getString("Description"));
				orderItem.setQuantity(rs.getInt("Quantity"));
				orderItem.setPrice(rs.getDouble("Price"));
				orderItems.add(orderItem);
			}
			return orderItems;
		} catch (Exception e){
			throw new RuntimeException("Error in getting order items for orderNo - " + orderNo, e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public long createOrder(Order order){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmt2 = null;
		try{
			con = DBConnector.getConnection();
			
			// insert order
			stmt = con.prepareStatement("INSERT INTO ORDERS(OrderNo, OrderDate, CustomerNo) VALUES (seq_orders.nextval, ?, ?)", new String[]{"OrderNo"});
			stmt.setDate(1, DateUtil.toSqlDate(order.getOrderDate()));			
			stmt.setInt(2, order.getCustomerNo());
			stmt.executeUpdate();
			
			// retrieve order no
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				long orderNo = rs.getLong(1);
				
				// insert order items
				stmt2 = con.prepareStatement("INSERT INTO ORDER_ITEMS(OrderNo, ProductId, Quantity, Price) SELECT ?, p.ProductId, ?, p.ListPrice FROM PRODUCTS p WHERE p.ProductId=?");
				for(OrderItem orderItem : order.getOrderItems()) {
					stmt2.setLong(1, orderNo);
					stmt2.setInt(2, orderItem.getQuantity());
					stmt2.setInt(3, orderItem.getProductId());
					stmt2.executeUpdate();
				}
				
				// commit
				con.commit();
				// return order no
				return orderNo;
			}
			else {
				throw new RuntimeException("Error generating order number for Order - " + order);
			}
		} catch (Exception e){
			throw new RuntimeException("Error inserting order - " + order, e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(stmt2!=null) stmt2.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shipOrder(long orderNo){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("UPDATE Orders SET ShipDate = SYSDATE WHERE OrderNo = ?");
			stmt.setLong(1, orderNo);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error shipping Order No - " + orderNo, e);
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void cancelOrder(long orderNo){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("UPDATE Orders SET CancelDate = SYSDATE WHERE OrderNo = ?");
			stmt.setLong(1, orderNo);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error canceling Order No - " + orderNo, e);
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
