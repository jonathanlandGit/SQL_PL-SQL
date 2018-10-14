package ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ss.Customer;
import ss.util.DateUtil;

public class CustomerDAO {
	
	public ArrayList<Customer> getCustomers() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT CustomerNo, Fname, Lname, Bdate, Email, Address, HomePhone, Mobile FROM CUSTOMERS");
			
			ArrayList<Customer> customers = new ArrayList<Customer>();
			while(rs.next()){
				Customer customer = new Customer();
				customer.setCustomerNo(rs.getInt("CustomerNo"));
				customer.setFirstName(rs.getString("Fname"));
				customer.setLastName(rs.getString("Lname"));
				customer.setBirthDate(rs.getDate("Bdate"));
				customer.setEmail(rs.getString("Email"));
				customer.setAddress(rs.getString("Address"));
				customer.setHomePhone(rs.getLong("HomePhone"));
				customer.setMobile(rs.getLong("Mobile"));
				customers.add(customer);
			}
			return customers;
		} catch (SQLException e){
			throw new RuntimeException("Error in getting customers.", e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Customer getCustomer(int customerNo) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("SELECT CustomerNo, Fname, Lname, Bdate, Email, Address, HomePhone, Mobile FROM CUSTOMERS WHERE customerNo = ?");
			stmt.setInt(1, customerNo);
			rs = stmt.executeQuery();
			if(rs.next()){
				Customer customer = new Customer();
				customer.setCustomerNo(rs.getInt("CustomerNo"));
				customer.setFirstName(rs.getString("Fname"));
				customer.setLastName(rs.getString("Lname"));
				customer.setBirthDate(rs.getDate("Bdate"));
				customer.setEmail(rs.getString("Email"));
				customer.setAddress(rs.getString("Address"));
				customer.setHomePhone(rs.getLong("HomePhone"));
				customer.setMobile(rs.getLong("Mobile"));
				return customer;
			} else
				return null;
		} catch (Exception e){
			throw new RuntimeException("Error in getting customer for no = " + customerNo, e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int insertCustomer(Customer customer){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("INSERT INTO CUSTOMERS(CustomerNo, Fname, Lname, Bdate, Email, Address, HomePhone, Mobile) VALUES (seq_customers.nextval, ?, ?, ?, ?, ?, ?, ?)", new String[]{"CustomerNo"});
			stmt.setString(1, customer.getFirstName());			
			stmt.setString(2, customer.getLastName());
			stmt.setDate(3, DateUtil.toSqlDate(customer.getBirthDate()));
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getAddress());
			stmt.setLong(6, customer.getHomePhone());
			stmt.setLong(7, customer.getMobile());
			stmt.executeUpdate();
			
			// retrieve customer no
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				int customerNo = rs.getInt(1);
				con.commit();
				return customerNo;
			}
			else {
				throw new RuntimeException("Error generating customer no for " + customer);
			}
		} catch (Exception e){
			throw new RuntimeException("Error inserting customer - " + customer, e);
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
	
	public void updateCustomer(Customer customer){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("UPDATE CUSTOMERS SET FName = ?, LName = ?, BDate = ?, Email = ?, Address = ?, HomePhone = ?, Mobile = ?  WHERE CustomerNo = ?");
			stmt.setString(1, customer.getFirstName());			
			stmt.setString(2, customer.getLastName());
			stmt.setDate(3, DateUtil.toSqlDate(customer.getBirthDate()));
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getAddress());
			stmt.setLong(6, customer.getHomePhone());
			stmt.setLong(7, customer.getMobile());
			stmt.setInt(8, customer.getCustomerNo());
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error updating customer - " + customer, e);
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
	
	public void deleteCustomer(int customerNo){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("DELETE FROM CUSTOMERS WHERE customerno = ?");
			stmt.setInt(1, customerNo);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error updating product - " + customerNo, e);
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
