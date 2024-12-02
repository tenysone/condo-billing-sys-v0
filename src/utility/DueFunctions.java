package utility;

import java.io.IOException;

public interface DueFunctions {
    abstract void issueDue() throws IOException;
    abstract void issuePenalty(String DueRefNo) throws IOException;
    abstract void displayDue(String UnitNo,String OwnerID) throws IOException;
}
