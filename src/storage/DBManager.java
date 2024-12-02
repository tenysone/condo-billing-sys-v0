package storage;

import model.Due;
import model.Payment;
import utility.DueFunctions;
import utility.OwnerFunctions;
import utility.PaymentFunctions;
import utility.UnitFunctions;

import java.io.IOException;
import java.time.LocalDate;

import static utility.Util.*;

public class DBManager extends Database implements
        UnitFunctions, OwnerFunctions, DueFunctions, PaymentFunctions {
    /*
    *   Protected instantiations of DB classes
    *   - Centralizes all functions to DBManager
    *   - Allows one instance of an object to do various functions
    */
    protected static OwnerDB ownerDB= new OwnerDB();
    protected static UnitDB unitDB= new UnitDB();
    protected static DueDB dueDB= new DueDB();
    protected static PaymentDB paymentDB= new PaymentDB();

    // Add default values
    public static void addDefault() throws IOException {
        unitDB.addUnit();
        ownerDB.addOwner();
        dueDB.issueDue();
    }

    /*  Method: 'addUnit'
    *   - Takes user-input and adds unit object to hash table
    *   @param none
    *   @return none
    */
    @Override
    public void addUnit() throws IOException {
        while (true) {
            System.out.print("Enter Unit Number: ");
            String UnitNo = br.readLine();
            if(units.containsKey(UnitNo)){
                System.out.println("Unit already exists.");
                UnitNo= getNewValue("Enter another Unit Number");
                if(UnitNo.isEmpty()){
                    System.out.println("Terminating Unit addition process...");
                    addBorder();
                    return;
                }
            }
            System.out.print("Enter Floor Location: ");
            String Floor = br.readLine();
            System.out.print("Enter Unit Area: ");
            String UnitArea = br.readLine();
            System.out.print("Enter Unit Price: ");
            double UnitPrice = sc.nextDouble();
            System.out.print("Enter Monthly Installment: ");
            double MonthlyInstallment = sc.nextDouble();
            addBorder();
            unitDB.addUnit(UnitNo, Floor, UnitArea, UnitPrice, MonthlyInstallment);
            addBorder();

            if(!willDoAgain("Add another Unit?")){
                System.out.println("Terminating Unit addition process...");
                addBorder();
                return;
            }
            addBorder();
        }
    } //admin

    /*
    *   Method: 'displayUnit'
    *   - nuff said, displays units regardless of subtype
    */
    @Override
    public void displayUnit() throws IOException {
        unitDB.displayUnit();
    } //admin

    /*  Method: 'addOwner'
     *   - Takes user-input and adds owner object to hash table
     *   @param none
     *   @return none
     */
    @Override
    public void addOwner() throws IOException {
        while (true) {
            System.out.print("Enter Owner ID: ");
            String OwnerID = br.readLine();
            if(owners.containsKey(OwnerID)){
                System.out.println("Owner already exists.");
                OwnerID= getNewValue("Enter another Owner ID");
                if(OwnerID.isEmpty()){
                    System.out.println("Terminating Owner addition process...");
                    addBorder();
                    return;
                }
            }
            System.out.print("Enter Name: ");
            String Name = br.readLine();
            System.out.print("Enter Contact Number: ");
            String ContactNo = br.readLine();
            System.out.print("Enter Email Address: ");
            String EmailAdd = br.readLine();
            System.out.print("Enter Unit Number: ");
            String UnitNo = br.readLine();
            addBorder();
            ownerDB.addOwner(OwnerID, Name, ContactNo, EmailAdd, UnitNo);
            addBorder();

            if(!willDoAgain("Add another Owner?")){
                System.out.println("Terminating Owner addition process...");
                addBorder();
                return;
            }
        }
    } //admin

    // Edits Owner details safely, layered with validation checks
    @Override
    public void editOwner(String OwnerID) throws IOException {
        ownerDB.editOwner(OwnerID);
    } //owner

    // Displays all Owners
    @Override
    public void displayOwner() throws IOException {
        ownerDB.displayOwner();
    } //admin

    // Displays details of one specific owner
    @Override
    public void displayOwnerDetails(String OwnerID) throws IOException {
        ownerDB.displayOwnerDetails(OwnerID);
    } //owner

    // Links an Owner to one or more Units thru hashmap
    @Override
    public void registerUnits(String UnitNo, String OwnerID) throws IOException {
        while(true){
            System.out.print("Enter Unit Number: ");
            String inUnitNo= br.readLine();
            System.out.print("Enter Owner ID: ");
            String inOwnerID= br.readLine();
            addBorder();
            ownerDB.registerUnits(inUnitNo,inOwnerID);
            addBorder();

            if(!willDoAgain("Register another Unit?")){
                System.out.println("Terminating registration process...");
                return;
            }
        }
    } //admin

    // Displays all owned units by an Owner
    @Override
    public void displayOwnedUnits(String OwnerID) throws IOException {
        ownerDB.displayOwnedUnits(OwnerID);
    } //owner

    public String getOwnerID(String OwnerID){
        owner= owners.get(OwnerID);
        return owner.getName();
    }

    public boolean getOwnerKey(String Username){
        return owners.containsKey(Username);
    }

    @Override
    public void issueDue() throws IOException {
        while(true){
            System.out.print("Enter Due Reference Number: ");
            String DueRefNo= br.readLine();

            if(dues.containsKey(DueRefNo)){
                System.out.println("Due Reference already exists.");
                DueRefNo= getNewValue("Enter a new Due Reference Number");
                if(DueRefNo.isEmpty()){
                    System.out.println("Terminating Billing Process...");
                    return;
                }
            }
            System.out.print("Enter Unit Number for Due: ");
            String UnitNo= br.readLine();

            if(!units.containsKey(UnitNo)){
                System.out.println("Unit Number does not exist.");
                UnitNo= getNewValue("Enter a new Unit Number");
                if(UnitNo.isEmpty()){
                    System.out.println("Terminating Billing process...");
                    return;
                }
            }
            unit= units.get(UnitNo);
            if(unit.getOwnerID()==null){
                System.out.println("This Unit is not occupied");
                UnitNo= getNewValue("Enter a new Unit Number");
                if(UnitNo.isEmpty()){
                    System.out.println("Terminating Billing process...");
                    return;
                }
            }

            addBorder();
            System.out.println("Enter Date of Issue (YYYY-MM-D)");
            addBorder();

            LocalDate IssueDate;
            LocalDate DueDate;
            while(true){
                System.out.print("Enter Year: ");
                int year= sc.nextInt();
                System.out.print("Enter Month: ");
                int month= sc.nextInt();
                System.out.print("Enter Day: ");
                int day= sc.nextInt();

                if(isValidDate(year,month,day)){
                    IssueDate= LocalDate.of(year,month,day);
                    DueDate= IssueDate.plusDays(14);
                    break;
                }
            }
            addBorder();
            System.out.print("Enter Water Dues: ");
            double WaterDue= sc.nextDouble();
            System.out.print("Enter Electricity Dues: ");
            double ElecDues= sc.nextDouble();
            System.out.print("Enter Association Dues: ");
            double AssocDues= sc.nextDouble();
            addBorder();

            unit= units.get(UnitNo);
            due= new Due(DueRefNo,unit,DueDate,IssueDate,WaterDue,ElecDues,AssocDues);
            dueDB.issueDue(due);

            if(!willDoAgain("Issue another Bill?")){
                System.out.println("Terminating Billing process...");
                return;
            }

            addBorder();
        }
    }

    @Override
    public void issuePenalty(String DueRefNo) throws IOException {
        System.out.print("Enter Due Reference Number: ");
        DueRefNo= br.readLine();
        dueDB.issuePenalty(DueRefNo);
        addBorder();
    }

    @Override
    public void displayDue(String UnitNo, String OwnerID) throws IOException {
        System.out.print("Enter Unit Number: ");
        UnitNo= br.readLine();
        dueDB.displayDue(UnitNo,OwnerID);
    }

    @Override
    public void issuePayment(String ownerID) throws IOException {
        String DueRefNo, PayRefNo;
        double AmountPaid;
        LocalDate DatePaid;

        while(true){
            while(true){
                System.out.print("Enter Due Reference Number: ");
                DueRefNo= br.readLine();

                if(!isValidRef(DueRefNo,ownerID)){
                    DueRefNo= getNewValue("Enter a new Due Reference Number");
                    if(DueRefNo.isEmpty()){
                        System.out.println("Terminating Payment Process...");
                        return;
                    }
                }
                break;
            }

            while(true){
                System.out.print("Enter Payment Reference Number: ");
                PayRefNo= br.readLine();

                if(payments.containsKey(PayRefNo)){
                    System.out.println("Payment Reference Number already exists.");
                    PayRefNo= getNewValue("Enter a new Payment Reference Number");
                    if(PayRefNo.isEmpty()){
                        System.out.println("Terminating Payment Process...");
                        return;
                    }
                }
                break;
            }

            while(true){
                System.out.print("Enter Payment Amount: ");
                AmountPaid= sc.nextDouble();

                if(!isValidPayment(DueRefNo,AmountPaid)){
                    String newPayment= getNewValue("Enter a new Amount");
                    if(newPayment.isEmpty()){
                        System.out.println("Terminating Payment Process...");
                        return;
                    } else{
                        AmountPaid= Double.parseDouble(newPayment);
                    }
                }
                break;
            }

            addBorder();
            System.out.println("Enter Payment Date (YYYY-MM-D)");
            addBorder();

            while(true){
                System.out.print("Enter Year: ");
                int year= sc.nextInt();
                System.out.print("Enter Month: ");
                int month= sc.nextInt();
                System.out.print("Enter Day: ");
                int day= sc.nextInt();

                if(isValidDate(year,month,day)){
                    DatePaid= LocalDate.of(year,month,day);
                    break;
                }
            }
            due= dues.get(DueRefNo);
            payment= new Payment(PayRefNo,due,DatePaid,AmountPaid);
            payments.put(PayRefNo,payment);
            due.setPayment(payment);
            due.setStatus("PAID");

            System.out.println("Payment Success.");

            if(!willDoAgain("Issue another Payment?")){
                System.out.println("Terminating Payment process...");
                return;
            }
        }
    }

    @Override
    public void displayPaymentRecord() throws IOException {
        paymentDB.displayPaymentRecord();
    }

    private boolean isValidRef(String DueRefNo, String OwnerID){
        while(true){
            if(!dues.containsKey(DueRefNo)){
                System.out.println("Due Reference Number does not exist.");
                return false;
            }
            due= dues.get(DueRefNo);
            if(due.getOwnerID()==null){
                System.out.println("This Due is not issued for any Owner.");
                return false;
            } else if(!due.getOwnerID().equals(OwnerID)){
                System.out.println("This Due is issued for another owner.");
                return false;
            }
            if(due.getPayment()!=null){
                System.out.println("This Due is already paid for.");
                return false;
            }
            return true;
        }
    }

    private boolean isValidPayment(String DueRefNo,double Payment){
        due= dues.get(DueRefNo);

        if(Payment < due.getTotalBill()){
            System.out.println("Insufficient Payment");
            System.out.println("Please pay exactly " + due.getTotalBill());
            return false;
        } else if(Payment > due.getTotalBill()){
            System.out.println("Payment exceeds the Due");
            System.out.println("Please pay exactly " + due.getTotalBill());
            return false;
        } else{
            return true;
        }
    }
}
