// src/model/WeighingDetail.java
package model;

public class WeighingDetail {
    private int detailID;
    private int sessionID;
    private int bagIndex;
    private double weightKg;

    public int getDetailID() { return detailID; }
    public void setDetailID(int detailID) { this.detailID = detailID; }

    public int getSessionID() { return sessionID; }
    public void setSessionID(int sessionID) { this.sessionID = sessionID; }

    public int getBagIndex() { return bagIndex; }
    public void setBagIndex(int bagIndex) { this.bagIndex = bagIndex; }

    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
}