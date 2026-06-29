package com.wipro.phms.entity;

public class Customer {
	private String customerId;
	private String customerName;
	private String contactNumber;
	public Customer(String customerId,String customerName,String contactNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
	}
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String toString() {
    	return "Customer ID:"+customerId
    			+"\n Customer Name:"+customerName
    			+"\n ContactNumber:"+contactNumber;
    }
}