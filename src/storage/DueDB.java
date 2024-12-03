package storage;

import model.Due;
import model.Unit;
import model.Unpaid;
import utility.DueFunctions;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;

import static utility.Util.*;

public class DueDB extends Database implements DueFunctions {

    // Adds default due values
    @Override
    public void issueDue() throws IOException {
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b) {
            }
        }));

        issueDue(new Due("ARB-01", units.get("AXRB-01"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 500, 1300, 4000));
        issueDue(new Due("ARB-01", units.get("AXRB-01"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 500, 1300, 4000));
        issueDue(new Due("ARB-02", units.get("AXRB-06"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 600, 1400, 4100));
        issueDue(new Due("ARB-03", units.get("AXRB-09"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 700, 1500, 4200));
        issueDue(new Due("ARB-04", units.get("AXRB-01"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 550, 1350, 4050));
        issueDue(new Due("ARB-05", units.get("AXRB-07"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 650, 1450, 4150));
        issueDue(new Due("ARB-06", units.get("AXRB-06"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 800, 1550, 4250));
        issueDue(new Due("ARB-07", units.get("AXRB-01"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 600, 1400, 4100));
        issueDue(new Due("ARB-08", units.get("AXRB-08"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 750, 1600, 4300));
        issueDue(new Due("ARB-09", units.get("AXRB-09"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 850, 1650, 4400));
        issueDue(new Due("ARB-10", units.get("AXRB-10"), LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 24), 700, 1500, 4200));

        System.setOut(originalOut);
    }

    // Issues due values individually
    public void issueDue(Due due) throws IOException {
        while(true){
            unit = due.getUnit();

            String OwnerID = unit.getOwnerID();     // Sets ownerID to specific unit instance
            String DueRefNo = due.getDueRefNo();    // Fetches DueRefNo to @arg due

            due.setOwnerID(OwnerID);

            dues.put(DueRefNo, due);

            if(unit instanceof Unpaid){             // Sets monthly installment from Unpaid subclasses
                due.setMonthlyInstallment(((Unpaid) unit).getMonthlyInstallment());
            }

            calculateTotal(DueRefNo);

            unit.setDues(DueRefNo, due);
            System.out.println("Bill issued successfully.");
            return;
        }
    }

    /*
    *   Sets penalty field to due instance
    *   Checks if payment is already set to due
    * */
    @Override
    public void issuePenalty(String DueRefNo) throws IOException {
        due = dues.get(DueRefNo);
        unit = due.getUnit();

        //Check if status is already paid
        if (due.getStatus().equals("PAID")) {
            addBorder();
            System.out.println("Due is already paid for.");
            System.out.println(due.getStatus());
        } else { //Issue penalty
            due.setPenalty(due.getTotalBill() * 0.10);
            calculateTotal(DueRefNo);
            System.out.println("Penalty issued successfully.");
        }
    }

    /*
    *   Prints specific dues for each unit/owner
    *   Has validation checks to print only valid dues
    *   */
    @Override
    public void displayDue(String UnitNo, String OwnerID) throws IOException {
        unit = units.get(UnitNo);
        owner = owners.get(OwnerID);

        if (unit == null) { // Check if unit is null, print corresponding prompt
            System.out.println("Unit does not exist...");
        }

        // Check if the hashmap of units in owners carry a certain unit (identified thru UnitNo)
        if (!owner.getOwnedUnits().containsKey(UnitNo)) {
            System.out.println("This unit does not belong to owner " + owner.getName());
        } else {
            // Creates a copy of hashmap of dues in a given unit
            HashMap<String, Due> issuedBills = unit.getDues();

            if (issuedBills.isEmpty()) { // If hashmap of dues is empty, print corresponding prompt
                System.out.println("No Bills issued at this unit...");
            } else {
                addBorder(); // Prints a sort of header or table name to organize printed values
                System.out.printf("%-30s %-20s %-20s %-20s %-20s %-20s %-30s %-30s %-30s %-30s %n",
                        "Reference Number", "Date of Issue", "DueDate", "Status", "Electricity", "Water",
                        "Association Due", "Monthly Installment", "Penalty", "Total Bill");
                addBorder();
                for (Due i : issuedBills.values()) {
                    System.out.println(i.printForDue()); // Uses a modified toString method from 'Due' class
                }
                addBorder();
            }
        }
    }

    public void calculateTotal(String DueRefNo) {

        due = dues.get(DueRefNo);
        unit = due.getUnit();                               // Fetch unit from associated unit in dues

        double MI;
        if (unit instanceof Unpaid) {
            MI = ((Unpaid) unit).getMonthlyInstallment();   // Fetch value of monthly installment
            due.setMonthlyInstallment(MI);                  // Assign monthly installment to due
        }

        double WaterDue = due.getWaterDue();
        double ElecDue = due.getElecDue();
        double AssocDue = due.getAssocDue();
        double MonthlyInstallment = due.getMonthlyInstallment();
        double Penalties = due.getPenalty();
        double TotalBill = WaterDue + ElecDue + AssocDue + +MonthlyInstallment + Penalties; // Computational field for total bill

        due.setTotalBill(TotalBill);                        // Set new TotalBill
    }
}
