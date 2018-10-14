package ss;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private long orderNo;
	private Date orderDate;
	private int customerNo;
	private String customerName;
	private Date shipDate;
	private Date cancelDate;
	private double totalAmount;
	
	private ArrayList<OrderItem> orderItems;

	public Order() {
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", orderDate=" + orderDate
				+ ", customerNo=" + customerNo + ", customerName="
				+ customerName + ", shipDate=" + shipDate + ", cancelDate="
				+ cancelDate + ", totalAmount=" + totalAmount + ", orderItems="
				+ orderItems + "]";
	}
}
