package utility;

import java.io.IOException;

public interface PaymentFunctions {
    abstract void issuePayment(String ownerID) throws IOException;
    abstract void displayPaymentRecord() throws IOException;
}
