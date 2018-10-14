package ss;

public class CustomerSale {
	private String customerName;
	private double totalSalePrice;
	private double totalCost;
	private double profit;
	
	public CustomerSale() {
		
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public double getTotalSalePrice() {
		return totalSalePrice;
	}
	public void setTotalSalePrice(double totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "CustomerSale [customerName=" + customerName
				+ ", totalSalePrice=" + totalSalePrice + ", totalCost="
				+ totalCost + ", profit=" + profit + "]";
	}
}
