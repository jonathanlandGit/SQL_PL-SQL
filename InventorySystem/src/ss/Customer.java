package ss;

import java.util.Date;

public class Customer {
	private int customerNo;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String email;
	private String address;
	private long homePhone;
	private long mobile;
	
	public Customer() {
	}

	public int getCustomerNo() {
		return customerNo;
	}
	
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(long homePhone) {
		this.homePhone = homePhone;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Customer [customerNo=" + customerNo + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", birthDate=" + birthDate + ", email=" + email
				+ ", address=" + address + ", homePhone=" + homePhone
				+ ", mobile=" + mobile + "]";
	}	
}
