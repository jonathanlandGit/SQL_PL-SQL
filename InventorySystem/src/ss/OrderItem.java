package ss;

public class OrderItem {
	private long orderNo;
	private int productId;
	private String productDesc;
	private int quantity;
	private double price;
	
	public OrderItem() {
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getAmount() {
		return quantity * price;
	}

	@Override
	public String toString() {
		return "OrderItem [orderNo=" + orderNo + ", productId=" + productId
				+ ", productDesc=" + productDesc + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
}
