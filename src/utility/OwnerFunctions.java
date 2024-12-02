package utility;

import java.io.IOException;

public interface OwnerFunctions {
    abstract void addOwner() throws IOException;
    abstract void editOwner(String OwnerID) throws IOException;
    abstract void displayOwner() throws IOException;
    abstract void displayOwnerDetails(String OwnerID) throws IOException;
    abstract void registerUnits(String UnitNo, String OwnerID) throws IOException;
    abstract void displayOwnedUnits(String OwnerID) throws IOException;
}
