package model;

import java.util.HashMap;

public class Owner {
    /*  Relational Logic:
     *   - An OWNER has UNIT/s
     *   - A UNIT has DUE/s
     *   - A DUE has PAYMENT/s
     */
    private String OwnerID, Name, ContactNo, EmailAdd;
    private HashMap<String,Unit> ownedUnits= new HashMap<>();

    public Owner(String OwnerID, String Name, String ContactNo, String EmailAdd, String UnitNo, Unit unit){
        this.OwnerID= OwnerID;
        this.Name= Name;
        this.ContactNo= ContactNo;
        this.EmailAdd= EmailAdd;

        this.ownedUnits.put(UnitNo,unit);
    }


    public void setOwnedUnits(String UnitNo, Unit unit) {
        this.ownedUnits.put(UnitNo, unit); //take unit no and take unit entry
    }
    public HashMap<String, Unit> getOwnedUnits() {
        return ownedUnits;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }
    public void setEmailAdd(String EmailAdd) {
        this.EmailAdd = EmailAdd;
    }
    public String getOwnerID() {
        return OwnerID;
    }
    public String getName() {
        return Name;
    }
    public String getContactNo() {
        return ContactNo;
    }
    public String getEmailAdd() {
        return EmailAdd;
    }

    @Override
    public String toString(){
        return String.format("%-20s %-40s %-30s %-40s %n", OwnerID, Name, ContactNo, EmailAdd);
    }
}
