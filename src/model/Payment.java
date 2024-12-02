package model;

import java.time.LocalDate;

public class Payment {
    /*  Relational Logic:
     *   - An OWNER has UNIT/s
     *   - A UNIT has DUE/s
     *   - A DUE has PAYMENT/s
     */
    private String PayRefNo;
    private LocalDate DatePaid;
    private double AmountPaid;
    private Due due;

    public Payment(String PayRefNo, Due due, LocalDate DatePaid, double AmountPaid){
        this.PayRefNo= PayRefNo;
        this.DatePaid= DatePaid;
        this.AmountPaid= AmountPaid;

        this.due= due;
    }

    public void setPayRefNo(String PayRefNo) {
        this.PayRefNo = PayRefNo;
    }
    public void setAmountPaid(double AmountPaid) {
        this.AmountPaid = AmountPaid;
    }
    public void setDatePaid(LocalDate DatePaid) {
        this.DatePaid = DatePaid;
    }
    public String getPayRefNo() {
        return PayRefNo;
    }
    public LocalDate getDatePaid() {
        return DatePaid;
    }
    public double getAmountPaid() {
        return AmountPaid;
    }

    @Override
    public String toString(){
        return String.format("%-10s %20s %20s",PayRefNo,AmountPaid,DatePaid);
    }
}
