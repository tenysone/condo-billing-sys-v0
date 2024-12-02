package model;

import java.util.HashMap;

public class Unit {
    /*  Relational Logic:
    *   - An OWNER has UNIT/s
    *   - A UNIT has DUE/s
    *   - A DUE has PAYMENT/s
    */
    private String UnitNo, Floor, UnitArea, Status, OwnerID;
    private double UnitPrice;
    private HashMap<String, Due> dues = new HashMap<>(); //

    public Unit(String UnitNo, String Floor, String UnitArea, String Status, double UnitPrice){
        this.UnitNo= UnitNo;
        this.Floor= Floor;
        this.UnitArea= UnitArea;
        this.Status= Status;
        this.UnitPrice= UnitPrice;
    }

    public void setDues(String DueRefNo, Due due) {
        this.dues.put(DueRefNo,due);
    }
    public HashMap<String, Due> getDues() {
        return dues;
    }

    public void setOwnerID(String ownerID) {
        OwnerID = ownerID;
    }
    public void setUnitNo(String UnitNo) {
        this.UnitNo = UnitNo;
    }
    public void setFloor(String Floor) {
        this.Floor = Floor;
    }
    public void setUnitArea(String UnitArea) {
        this.UnitArea = UnitArea;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }


    public String getUnitNo() {
        return UnitNo;
    }
    public String getFloor() {
        return Floor;
    }
    public String getUnitArea() {
        return UnitArea;
    }
    public String getStatus() {
        return Status;
    }
    public double getUnitPrice() {
        return UnitPrice;
    }
    public String getOwnerID() {
        return OwnerID;
    }

    public String printForOwners(){
        return String.format("%-20s %-20s %-15s",UnitNo,Floor,Status);
    }

    @Override
    public String toString(){
        return String.format("%-10s %-15s %-15s %-20s %-30s", UnitNo, Floor, UnitArea, Status, UnitPrice);
    }
}
