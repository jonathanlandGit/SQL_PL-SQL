package ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ss.Product;

public class ProductDAO {
	
	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT ProductId, p.Description, pc.Description CategoryDescription, AvailableQuantity, Cost, ListPrice"
					+ " FROM PRODUCTS p JOIN PRODUCT_CATEGORY pc ON p.CategoryCode=pc.code ORDER BY ProductId");
			while(rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt("ProductId"));
				product.setDescription(rs.getString("Description"));
				product.setCategoryDescription(rs.getString("CategoryDescription"));
				product.setAvailableQuantity(rs.getInt("AvailableQuantity"));
				product.setCost(rs.getDouble("Cost"));
				product.setListPrice(rs.getDouble("ListPrice"));
				products.add(product);
			}
		} catch (Exception e){
			throw new RuntimeException("Error in getting products.", e);
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
		return products;
	}
	
	public Product getProduct(int productId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("SELECT ProductId, p.Description, CategoryCode, pc.Description CategoryDescription, AvailableQuantity, Cost, ListPrice"
					+ " FROM PRODUCTS p JOIN PRODUCT_CATEGORY pc ON p.CategoryCode=pc.code WHERE ProductId = ?");
			stmt.setInt(1,productId);
			rs = stmt.executeQuery();
			if(rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt("ProductId"));
				product.setDescription(rs.getString("Description"));
				product.setCategoryCode(rs.getString("CategoryCode"));
				product.setCategoryDescription(rs.getString("CategoryDescription"));
				product.setAvailableQuantity(rs.getInt("AvailableQuantity"));
				product.setCost(rs.getDouble("Cost"));
				product.setListPrice(rs.getDouble("ListPrice"));
				return product;
			} else
				return null;
		} catch (Exception e){
			throw new RuntimeException("Error in getting product for id = " + productId, e);
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
	
	public int insertProduct(Product product){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("INSERT INTO PRODUCTS(ProductId, Description, CategoryCode, AvailableQuantity, Cost, ListPrice) VALUES (seq_products.nextval, ?, ?, ?, ?, ?)", new String[]{"ProductId"});
			stmt.setString(1, product.getDescription());			
			stmt.setString(2, product.getCategoryCode());
			stmt.setInt(3, product.getAvailableQuantity());
			stmt.setDouble(4, product.getCost());
			stmt.setDouble(5, product.getListPrice());
			stmt.executeUpdate();
			
			// retrieve product id
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				int productId = rs.getInt(1);
				con.commit();
				return productId;
			}
			else {
				throw new RuntimeException("Error generating product id for " + product);
			}
		} catch (Exception e){
			throw new RuntimeException("Error inserting product - " + product, e);
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
	
	public void updateProduct(Product product){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("UPDATE PRODUCTS SET Description = ?, CategoryCode = ?, AvailableQuantity = ?, Cost = ?, ListPrice = ? WHERE ProductId = ?");
			stmt.setString(1, product.getDescription());			
			stmt.setString(2, product.getCategoryCode());
			stmt.setInt(3, product.getAvailableQuantity());
			stmt.setDouble(4, product.getCost());
			stmt.setDouble(5, product.getListPrice());
			stmt.setInt(6, product.getProductId());
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error updating product - " + product, e);
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
	
	public void deleteProduct(int productId){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("DELETE FROM PRODUCTS WHERE productid = ?");
			stmt.setInt(1, productId);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error updating product - " + productId, e);
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
