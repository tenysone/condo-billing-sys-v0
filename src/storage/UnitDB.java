package storage;

import model.Paid;
import model.Unpaid;
import utility.UnitFunctions;

import java.io.IOException;
import java.time.LocalDate;

import static utility.Util.*;

public class UnitDB extends Database implements UnitFunctions {

    /*  Method: 'addUnit'
     *   - Puts default value to hashtable of Units
     *  @param none
     *  @ return none
     */
    @Override
    public void addUnit() throws IOException {
        units.put("AXRB-01", new Unpaid("AXRB-01","10th", "320 sqm", "UNPAID", 3000, 3000));
        units.put("AXRB-02", new Unpaid("AXRB-02", "1st", "300 sqm", "UNPAID", 2800, 250));
        units.put("AXRB-03", new Unpaid("AXRB-03", "2nd", "350 sqm", "PAID", 3200, 350));
        units.put("AXRB-04", new Unpaid("AXRB-04", "3rd", "310 sqm", "UNPAID", 2900, 200));
        units.put("AXRB-05", new Unpaid("AXRB-05", "4th", "400 sqm", "UNPAID", 4000, 400));
        units.put("AXRB-06", new Unpaid("AXRB-06", "5th", "280 sqm", "UNPAID", 2600, 150));
        units.put("AXRB-07", new Unpaid("AXRB-07", "6th", "360 sqm", "UNPAID", 3400, 300));
        units.put("AXRB-08", new Unpaid("AXRB-08", "7th", "330 sqm", "UNPAID", 3100, 250));
        units.put("AXRB-09", new Unpaid("AXRB-09", "8th", "290 sqm", "UNPAID", 2700, 180));
        units.put("AXRB-10", new Unpaid("AXRB-10", "9th", "380 sqm", "UNPAID", 3700, 350));
    }

    /*  Overloaded method: 'addUnit(multiple param)'
     *  - Puts user-input value to hashtable of Units
     *  - Validates if a key ID is unique in the hash table
     *  @param multiple
     *  @ return none
     */
    public void addUnit(String UnitNo, String Floor, String UnitArea, double UnitPrice,
                    double MonthlyInstallment) {
        if(units.containsKey(UnitNo)){
            System.out.println("Unit already exists.");
        } else{
            units.put(UnitNo, new Unpaid(UnitNo,Floor,UnitArea,"UNPAID",UnitPrice,MonthlyInstallment));
            System.out.println("Adding Unit...");
            System.out.println("Successfully added a Unit.");
        }
    }

    /*
     *   Method: 'displayUnit'
     *   - Fetch values of a certain field
     *   - Return '0' or 'null' if non-existent
     *   - Display all Units without restriction on Unpaid or Paid subtype
     */
    @Override
    public void displayUnit() throws IOException {
        addBorder();
        System.out.printf("%-10s %20s %20s %20s %20s %20s %30s %30s %20s%n", "Unit Number", "Floor", "Unit Area", "Status",
                "Unit Price", "Amount Paid", "Monthly Installment", "Date Paid","OwnerID");
        addBorder();
        for(String i : units.keySet()){
            unit= units.get(i);
            System.out.printf("%-10s %20s %20s %20s %20s %20s %30s %30s %20s %n", unit.getUnitNo(), unit.getFloor(), unit.getUnitArea(),
                    unit.getStatus(), unit.getUnitPrice(), getAmountPaid(i), getMonthlyInstallment(i), getDatePaid(i),getOwnerID(i));
        }
        addBorder();
    }

    /*
    *   Fetcher methods
    *   - Fetch values of a certain field
    *   - Return '0' or 'null' if non-existent
    *   - Used to accomodate multiple statuses of Units
    */
    public double getAmountPaid(String UnitNo){
        unit= units.get(UnitNo);
        if(unit instanceof Paid)
            return ((Paid)unit).getAmountPaid();
        else
            return 0;
    }

    public double getMonthlyInstallment(String UnitNo){
        unit= units.get(UnitNo);
        if(unit instanceof Unpaid)
            return ((Unpaid)unit).getMonthlyInstallment();
        else
            return 0;
    }

    public LocalDate getDatePaid(String UnitNo){
        unit= units.get(UnitNo);
        if(unit instanceof Paid)
            return ((Paid)unit).getDatePaid();
        else
            return null; // return null if no applicable value is found
    }

    public String getOwnerID(String UnitNo){
        unit= units.get(UnitNo);
        if(unit.getOwnerID()==null){
            return "No owner yet";
        } else{
            return unit.getOwnerID();
        }
    }

}
