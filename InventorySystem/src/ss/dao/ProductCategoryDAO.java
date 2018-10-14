package ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ss.ProductCategory;

public class ProductCategoryDAO {
	public ArrayList<ProductCategory> getProductCategories() {
		ArrayList<ProductCategory> productcategories = new ArrayList<ProductCategory>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT Code, Description FROM PRODUCT_CATEGORY");
			while(rs.next()){
				ProductCategory productcategory = new ProductCategory();
				productcategory.setCode(rs.getString("Code"));
				productcategory.setDescription(rs.getString("Description"));
				productcategories.add(productcategory);
			}
		} catch (Exception e){
			throw new RuntimeException("Error in getting productcategories.", e);
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
		return productcategories;
	}
	
	public ProductCategory getProductCategory(String code) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("SELECT Code, Description FROM PRODUCT_CATEGORY WHERE Code= ?");
			stmt.setString(1,code);
			rs = stmt.executeQuery();
			if(rs.next()){
				ProductCategory productCategory = new ProductCategory();
				productCategory.setCode(rs.getString("Code"));
				productCategory.setDescription(rs.getString("Description"));
				return productCategory;
			} else
				return null;
		} catch (Exception e){
			throw new RuntimeException("Error in getting productcategory for code = " + code, e);
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
	
	public void insertProductCategory(ProductCategory productCategory){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("INSERT INTO PRODUCT_CATEGORY(Code, Description) VALUES (?, ?)");
			stmt.setString(1, productCategory.getCode());			
			stmt.setString(2, productCategory.getDescription());
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error inserting productcategory - " + productCategory, e);
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
	
	public void updateproductCategory(ProductCategory productCategory){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("UPDATE PRODUCT_CATEGORY SET Description = ? WHERE Code = ?");
			stmt.setString(1, productCategory.getDescription());	
			stmt.setString(2, productCategory.getCode());
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error updating productCategory - " + productCategory, e);
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteProductCategory(String code){
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnector.getConnection();
			stmt = con.prepareStatement("DELETE FROM PRODUCT_CATEGORY WHERE Code = ?");
			stmt.setString(1, code);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e){
			throw new RuntimeException("Error updating product - " + code, e);
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
