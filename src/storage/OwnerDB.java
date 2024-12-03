package storage;

import model.Owner;
import model.Unit;
import utility.OwnerFunctions;

import java.io.IOException;
import java.util.HashMap;

import static utility.Util.*;

public class OwnerDB extends Database implements OwnerFunctions {
    /*
    *   Method: 'addOwner'
    *   - Adds default Owners to the hash table
    *   @param none
    *   @return none
    */
    @Override
    public void addOwner() throws IOException {
        owners.put("OW-01", new Owner("OW-01","Glenndale C. Jumaquio", "199-201", "blanc@gmail.com","AXRB-01",units.get("AXRB-01")));
            unit= units.get("AXRB-01");
            unit.setOwnerID("OW-01");
        owners.put("OW-02", new Owner("OW-02", "Jazztin P. Vizconde", "202-204", "jazz.cruz@example.com","AXRB-06",units.get("AXRB-06")));
            unit= units.get("AXRB-06");
            unit.setOwnerID("OW-02");
        owners.put("OW-03", new Owner("OW-03", "Andrew S. Pacheco", "301-303", "drew.diaz@example.com","AXRB-07",units.get("AXRB-07")));
            unit= units.get("AXRB-07");
            unit.setOwnerID("OW-03");
        owners.put("OW-04", new Owner("OW-04", "Carla S. Naguit", "401-403", "clara.reyes@example.com","AXRB-08",units.get("AXRB-08")));
            unit= units.get("AXRB-08");
            unit.setOwnerID("OW-04");
        owners.put("OW-05", new Owner("OW-05", "Kahlil T. Quiambao", "501-503", "kei.velasco@example.com","AXRB-09",units.get("AXRB-09")));
            unit= units.get("AXRB-09");
            unit.setOwnerID("OW-02");
        owners.put("OW-06", new Owner("OW-06", "Joshua P. Jacinto", "601-603", "josh.santos@example.com","AXRB-10",units.get("AXRB-10")));
            unit= units.get("AXRB-10");
            unit.setOwnerID("OW-06");
    }

    /*
    *   Overloaded method: 'addOwner(multiple param)'
    *   - Puts user-defined Owner instance to hash table of Owners
    *   - Checks if a unit is already in another owner's hashmap of units
    *   @param multiple
    *   @return none
    */
    public void addOwner(String OwnerID, String Name, String ContactNo, String EmailAdd, String UnitNo) throws IOException {
        if(owners.containsKey(OwnerID)){
            System.out.println("Owner already exists.");
        } else{
            for(Owner i : owners.values()){
                if(i.getOwnedUnits().containsKey(UnitNo)){
                    System.out.println("Unit is already owned.");
                    return;
                }
            }
            owners.put(OwnerID, new Owner(OwnerID,Name,ContactNo,EmailAdd,UnitNo,units.get(UnitNo)));
            unit= units.get(UnitNo);
            unit.setOwnerID(OwnerID);
            System.out.println("Successfully added an Owner.");
        }
    }

    @Override
    public void editOwner(String OwnerID) throws IOException {
        owner= owners.get(OwnerID);

        if(owner==null){
            System.out.println("Owner does not exist.");
            System.out.println("Terminating Owner editing process...");
        } else{
            while(true){
                System.out.println("""
                    1.Edit Contact Number
                    2.Edit Email Address
                    0.Back to Home Page
                    """);
                String choice= getNonEmpty("What would you like to do?");
                switch(choice){
                    case "1" -> {
                        addBorder();
                        System.out.print("Enter new Contact Number: ");
                        String newVal= br.readLine();
                        owner= owners.get(OwnerID);
                        owner.setContactNo(newVal);
                        addBorder();
                        System.out.println("Updated value:\n");
                        displayOwnerDetails(OwnerID);
                        addBorder();
                    }
                    case "2" -> {
                        addBorder();
                        System.out.print("Enter new Email Address: ");
                        String newVal= br.readLine();
                        owner= owners.get(OwnerID);
                        owner.setEmailAdd(newVal);
                        addBorder();
                        System.out.println("Updated value:\n");
                        displayOwnerDetails(OwnerID);
                        addBorder();
                    }
                    case "0" -> {
                        System.out.println("Going back to Home Page...");
                        return;
                    }
                    default -> System.out.println("Invalid input. Please try again.");
                }
                if(!willDoAgain("Edit again")){
                    System.out.println("Terminating editing process...");
                    return;
                }
                addBorder();
            }
        }
    }

    // Prints all Owners
    @Override
    public void displayOwner() throws IOException {
        addBorder();
        System.out.printf("%-20s %-40s %-30s %-40s %n", "Owner ID", "Name", "Contact Number", "Email Address");
        addBorder();
        for(String i: owners.keySet()){
            System.out.println(owners.get(i));
        }
    }

    // Prints specific owner details with @param OwnerID
    @Override
    public void displayOwnerDetails(String OwnerID) throws IOException {
        owner= owners.get(OwnerID);
        System.out.printf("%-20s %-40s %-30s %-40s %n", "Owner ID", "Name", "Contact Number", "Email Address");
        System.out.println(owner.toString());
    }

    // Links units to owners
    @Override
    public void registerUnits(String UnitNo, String OwnerID) throws IOException {
        while(true){
            unit= units.get(UnitNo);
            owner= owners.get(OwnerID);

            if(owner==null){
                System.out.println("Owner ID does not exist.");
                return;
            }

            if(unit==null){
                System.out.println("Unit Number does not exist.");
                return;
            }

            for(Owner existingOwner : owners.values()){
                if(existingOwner.getOwnedUnits().containsKey(UnitNo)){
                    System.out.println("Unit is already owned.");
                    System.out.print("Proceed to replace Owner [y/n]? ");
                    String yn= br.readLine().toUpperCase();

                    if(yn.equals("Y")){
                        existingOwner.getOwnedUnits().remove(UnitNo);
                        System.out.println("Successfully replaced Owner.");
                    }else if (yn.equals("N")) {
                        System.out.println("Terminating registration process...");
                        return;
                    }else {
                        System.out.println("Invalid input. Please try again.");
                        return;
                    }
                } else if (owner.getOwnedUnits().containsKey(UnitNo)) {
                    System.out.println("Unit is already linked to this Owner.");
                    return;
                }
            }
            unit.setOwnerID(OwnerID);
            owner.setOwnedUnits(UnitNo,unit);
            System.out.println("Unit registration successful.");
            return;
        }
    }

    // Prints owned units of @param OwnerID
    @Override
    public void displayOwnedUnits(String OwnerID) throws IOException {
        owner= owners.get(OwnerID);
        if(owners==null){
            System.out.println("Owner not found: " + OwnerID);
            String newVal= getNewValue("Enter another Owner ID");
            if(!newVal.isEmpty())
                displayOwnedUnits(newVal);
            else
                System.out.println("Terminating Owner display...");
        } else{
            addBorder();
            System.out.println("Units owned by " + owner.getName() + " (" +owner.getOwnerID()+ ") :");

            HashMap<String, Unit> ownedUnits = owner.getOwnedUnits(); //

            if (ownedUnits.isEmpty()) {
                System.out.println("No Units owned...");
                System.out.println("Terminating Owner display...");
            } else {
                addBorder();
                System.out.printf("%-20s %-20s %-15s %n","Unit Number","Floor","Status");
                addBorder();
                for(Unit i : ownedUnits.values()){
                    System.out.println(i.printForOwners());
                }
            }
            addBorder();
        }
    }
}
