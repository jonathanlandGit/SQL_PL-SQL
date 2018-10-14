package ss;

public class Product {
	private int productId;
	private String description;
	private String categoryCode;
	private String categoryDescription;
	private int availableQuantity;
	private double cost;
	private double listPrice;
	
	public Product() {
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description="
				+ description + ", categoryCode=" + categoryCode
				+ ", categoryDescription=" + categoryDescription
				+ ", availableQuantity=" + availableQuantity + ", cost=" + cost
				+ ", listPrice=" + listPrice + "]";
	}
}
