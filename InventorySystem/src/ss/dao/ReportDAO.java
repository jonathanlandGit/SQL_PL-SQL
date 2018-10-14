package ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ss.CategorySale;
import ss.CustomerSale;
import ss.util.DateUtil;

public class ReportDAO {

	public ArrayList<CategorySale> getSalesByCategory(Date fromDate, Date toDate){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnector.getConnection();
			pstmt = con.prepareStatement("SELECT pc.description category_desc, SUM(quantity*price) total_sale_price, SUM(quantity*cost) total_cost, (SUM(quantity*price) - SUM(quantity*cost)) profit"
						+ " FROM orders o JOIN order_items oi ON o.orderno = oi.orderno JOIN products p ON oi.productid = p.productid JOIN product_category pc ON p.categorycode = pc.code"
						+ " WHERE o.orderdate BETWEEN ? AND ? AND o.canceldate IS NULL"
						+ " GROUP BY pc.description");
			pstmt.setDate(1, DateUtil.toSqlDate(fromDate));
			pstmt.setDate(2, DateUtil.toSqlDate(toDate));
			rs = pstmt.executeQuery();
			
			ArrayList<CategorySale> categorySales = new ArrayList<CategorySale>();
			while(rs.next()){
				CategorySale cs = new CategorySale();
				cs.setCategoryDesc(rs.getString("category_desc"));
				cs.setTotalSalePrice(rs.getDouble("total_sale_price"));
				cs.setTotalCost(rs.getDouble("total_cost"));
				cs.setProfit(rs.getDouble("profit"));
				categorySales.add(cs);
			}
			return categorySales;
		} catch (SQLException e) {
			throw new RuntimeException("Error in getting Sales by Category. ", e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<CustomerSale> getSalesByCustomer(Date fromDate, Date toDate){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnector.getConnection();
			pstmt = con.prepareStatement("SELECT c.fname||' '||c.lname customer_name, SUM(quantity*price) total_sale_price, SUM(quantity*cost) total_cost, (SUM(quantity*price) - SUM(quantity*cost)) profit"
						+ " FROM orders o JOIN order_items oi ON o.orderno = oi.orderno JOIN products p ON oi.productid = p.productid JOIN customers c ON o.customerno = c.customerno"
						+ " WHERE o.orderdate BETWEEN ? AND ? AND o.canceldate IS NULL"
						+ " GROUP BY c.fname||' '||c.lname");
			pstmt.setDate(1, DateUtil.toSqlDate(fromDate));
			pstmt.setDate(2, DateUtil.toSqlDate(toDate));
			rs = pstmt.executeQuery();
			
			ArrayList<CustomerSale> customerSales = new ArrayList<CustomerSale>();
			while(rs.next()){
				CustomerSale cs = new CustomerSale();
				cs.setCustomerName(rs.getString("customer_name"));
				cs.setTotalSalePrice(rs.getDouble("total_sale_price"));
				cs.setTotalCost(rs.getDouble("total_cost"));
				cs.setProfit(rs.getDouble("profit"));
				customerSales.add(cs);
			}
			return customerSales;
		} catch (SQLException e) {
			throw new RuntimeException("Error in getting Sales by Customer. ", e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
