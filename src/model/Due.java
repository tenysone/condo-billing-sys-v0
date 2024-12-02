package model;

import java.time.LocalDate;

public class Due {
    /*  Relational Logic:
     *   - An OWNER has UNIT/s
     *   - A UNIT has DUE/s
     *   - A DUE has PAYMENT/s
     */
    private String DueRefNo, Status, OwnerID;
    double WaterDue, ElecDue, AssocDue, MonthlyInstallment,Penalty, TotalBill;
    LocalDate DueDate, DateIssued;
    private Payment payment;
    private Unit unit;

    public Due(String DueRefNo, Unit unit, LocalDate DueDate, LocalDate DateIssued, double WaterDue,
               double ElecDue, double AssocDue){
        this.DueRefNo = DueRefNo;
        this.DateIssued= DateIssued;
        this.DueDate= DueDate;
        this.WaterDue= WaterDue;
        this.ElecDue= ElecDue;
        this.AssocDue= AssocDue;
        this.Status= "UNPAID";
        this.MonthlyInstallment= 0.0;
        this.Penalty= 0.0;
        this.TotalBill= 0.0;

        this.unit= unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public Unit getUnit() {
        return unit;
    }

    public void setDueRefNo(String DueRefNo) {
        this.DueRefNo = DueRefNo;
    }
    public void setDateIssued(LocalDate DateIssued) {
        this.DateIssued = DateIssued;
    }
    public void setDueDate(LocalDate DueDate) {
        this.DueDate = DueDate;
    }
    public void setWaterDue(double WaterDue) {
        this.WaterDue = WaterDue;
    }
    public void setElecDue(double ElecDue) {
        this.ElecDue = ElecDue;
    }
    public void setAssocDue(double AssocDue) {
        this.AssocDue = AssocDue;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public void setPenalty(double Penalty) {
        this.Penalty = Penalty;
    }
    public void setTotalBill(double totalBill) {
        TotalBill = totalBill;
    }
    public void setMonthlyInstallment(double monthlyInstallment) {
        MonthlyInstallment = monthlyInstallment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public void setOwnerID(String ownerID) {
        OwnerID = ownerID;
    }

    public String getDueRefNo() {
        return DueRefNo;
    }
    public LocalDate getDueDate() {
        return DueDate;
    }
    public LocalDate getDateIssued() {
        return DateIssued;
    }
    public double getWaterDue() {
        return WaterDue;
    }
    public double getElecDue() {
        return ElecDue;
    }
    public double getAssocDue() {
        return AssocDue;
    }
    public String getStatus() {
        return Status;
    }
    public double getPenalty() {
        return Penalty;
    }
    public double getTotalBill() {
        return TotalBill;
    }
    public double getMonthlyInstallment() {
        return MonthlyInstallment;
    }
    public Payment getPayment() {
        return payment;
    }
    public String getOwnerID() {
        return OwnerID;
    }

    public String printForPayments(){
        return String.format("%-30s %-20s %-30s %-30s ",
                DueRefNo,Status,TotalBill,OwnerID);
    }

    public String printForDue(){
        return String.format("%-30s %-20s %-20s %-20s %-20s %-20s %-30s %-30s %-30s %-30s %n",
                DueRefNo,DateIssued,DueDate,Status,ElecDue,WaterDue,AssocDue,MonthlyInstallment,Penalty,TotalBill);
    }

    @Override
    public String toString(){
        return String.format("%-30s %-30s %-30s %-30s %-30s %-20s  %-20s %n",
                DueRefNo,DateIssued,DueDate,Status,TotalBill, unit.getUnitNo(),OwnerID);
    }
}
