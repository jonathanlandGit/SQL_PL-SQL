package ss;

public class CategorySale {
	private String categoryDesc;
	private double totalSalePrice;
	private double totalCost;
	private double profit;
	
	public CategorySale() {
		
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
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
		return "CategorySale [categoryDesc=" + categoryDesc
				+ ", totalSalePrice=" + totalSalePrice + ", totalCost="
				+ totalCost + ", profit=" + profit + "]";
	}
}
