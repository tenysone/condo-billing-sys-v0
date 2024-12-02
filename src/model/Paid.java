package model;

import java.time.LocalDate;

public class Paid extends Unit{
    private double AmountPaid;
    private LocalDate DatePaid;
    public Paid(String UnitNo, String Floor, String UnitArea, String Status, double UnitPrice, double AmountPaid, LocalDate DatePaid){
        super(UnitNo,Floor,UnitArea,Status,UnitPrice);
        this.AmountPaid= AmountPaid;
        this.DatePaid= DatePaid;
    }
    public void setAmountPaid(double AmountPaid) {
        this.AmountPaid = AmountPaid;
    }
    public void setDatePaid(LocalDate datePaid) {
        DatePaid = datePaid;
    }
    public double getAmountPaid() {
        return AmountPaid;
    }
    public LocalDate getDatePaid() {
        return DatePaid;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("%-20s %-30s",AmountPaid,DatePaid);
    }
}
