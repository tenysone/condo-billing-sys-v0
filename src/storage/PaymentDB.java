package storage;

import model.Due;
import model.Payment;
import model.Unit;
import utility.PaymentFunctions;

import java.io.IOException;
import java.time.LocalDate;

import static utility.Util.*;

public class PaymentDB extends Database implements PaymentFunctions {

    @Override
    public void issuePayment(String PayRefNo) throws IOException {

    }

    public void issuePayment(String PayRefNo, Payment payment) throws IOException {
        PayRefNo= payment.getPayRefNo();

        payments.put(PayRefNo,payment);
        due.setPayment(payment);
        due.setStatus("PAID");

        System.out.println("Payment Success.");
    }

    @Override
    public void displayPaymentRecord() throws IOException {
        addBorder();
        System.out.printf("%-30s         %-30s %-20s %-30s %-30s %-10s %20s %20s %n",
                "Unit Number","Due Reference","Status","Total Bill","Owner","Payment",
                "Amount","Date Paid");
        addBorder();
        for(Unit units : units.values()){
            for(Due bills : units.getDues().values()){
                System.out.printf("%-30s         ",units.getUnitNo());
                System.out.println(bills.printForPayments() + bills.getPayment());
            }
        }
        addBorder();
    }
}
