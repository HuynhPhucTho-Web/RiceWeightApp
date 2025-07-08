// src/model/WeighingSession.java
package model;

import java.util.List;

public class WeighingSession {
    private int sessionID;
    private int customerID;
    private String weighingDate;
    private double pricePerKg;
    private String note;
    private List<WeighingDetail> details;

    public int getSessionID() { return sessionID; }
    public void setSessionID(int sessionID) { this.sessionID = sessionID; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getWeighingDate() { return weighingDate; }
    public void setWeighingDate(String weighingDate) { this.weighingDate = weighingDate; }

    public double getPricePerKg() { return pricePerKg; }
    public void setPricePerKg(double pricePerKg) { this.pricePerKg = pricePerKg; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public List<WeighingDetail> getDetails() { return details; }
    public void setDetails(List<WeighingDetail> details) { this.details = details; }
}
