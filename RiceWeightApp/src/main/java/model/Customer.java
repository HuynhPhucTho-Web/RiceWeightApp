// src/model/Customer.java// src/model/Customer.java
package model;

public class Customer {
    private int customerID;
    private String customerName;
    private String phone;

    public Customer() {}

    public Customer(int customerID, String customerName, String phone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phone = phone;
    }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
