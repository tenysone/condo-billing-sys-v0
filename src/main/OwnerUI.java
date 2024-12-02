package main;

import utility.UserFunctions;

import java.io.IOException;

import static main.Main.dbManager;
import static utility.Util.*;

public class OwnerUI implements UserFunctions {
    private static String currentUser;  // Global value of valid ownerID

    /*
     *   Method: 'mainPage'
     *   - Contains all user-related functions
     *   @param String:welcomeName to be used as a welcome header upon entry
     *   - Used for all user-based functions that require owner-identification
     *   @return none
     */
    @Override
    public void mainPage(String ownerID) throws IOException {
        String name= dbManager.getOwnerID(ownerID);
        addDoubleBorder();
        System.out.printf("%100s %n","Welcome " + name);
        addDoubleBorder();

        while(true) {
            System.out.println("""
                    1.View my Units
                    2.View my Bills
                    3.View My Details
                    4.Edit Contact Information
                    5.Issue Payments
                    0.Log-out
                    """);
            String choice= getNonEmpty("What would you like to do?");
            switch (choice) {
                case "1" -> {
                    dbManager.displayOwnedUnits(ownerID);
                }
                case "2" -> {
                    addBorder();
                    System.out.printf("%85s %n", "Bills");
                    addBorder();
                    String filler= "";
                    dbManager.displayDue(filler,ownerID);
                    addBorder();
                }
                case "3" -> {
                    addBorder();
                    System.out.printf("%86s %n", "My Details");
                    addBorder();
                    dbManager.displayOwnerDetails(ownerID);
                    addBorder();
                }
                case "4" -> {
                    addBorder();
                    System.out.printf("%87s %n", "Edit Details");
                    addBorder();
                    dbManager.editOwner(ownerID);
                    addBorder();
                }
                case "5" -> {
                    addBorder();
                    System.out.printf("%86s %n", "Payments");
                    addBorder();
                    dbManager.issuePayment(ownerID);
                }
                case "0" -> {
                    System.out.println("Logging-out...");
                    System.out.println("Successfully logged-out");
                    return;
                }
            }
        }
    }

    /*
     *   Method: 'loginPage'
     *   - Takes user details using nested ifs
     *   - If username is valid: proceed to password
     *   - If invalid: ask again/cancel log-in
     *   - Same goes for password
     *   @param none
     *   @return none
     */
    @Override
    public void loginPage() throws IOException {
        while(true){
            if(checkUsername()){
                if(checkPassword()){
                    mainPage(currentUser);
                    return;
                } else{
                    if(willDoAgain("Terminate Owner Log-in Process?")){
                        System.out.println("Going back to Home Page...");
                        return;
                    }
                }
            } else{
                if(willDoAgain("Terminate Owner Log-in Process?")){
                    System.out.println("Going back to Home Page...");
                    return;
                }
            }
        }
    }

    /*
     *   Method: 'checkUsername'
     *   - Persistent validation on whether username is valid or not
     *   - Allows to repeat the process on user-cue
     *   @param none
     *   @return boolean to signal whether username is valid or not
     */
    @Override
    public boolean checkUsername() throws IOException {
        while(true){
            System.out.print("Enter Username: ");
            String username= br.readLine();

            if(dbManager.getOwnerKey(username)){
                currentUser= username;
                return true;
            } else{
                System.out.println("Invalid Username.");
                if(!willDoAgain("Enter another Username?")){
                    return false;
                }
            }
        }
    }

    /*
     *   Method: 'checkPassword'
     *   - Persistent validation on whether password is valid or not
     *   - Allows to repeat the process on user-cue
     *   @param none
     *   @return boolean to signal whether password is valid or not
     */
    @Override
    public boolean checkPassword() throws IOException {
        while(true){
            System.out.print("Enter Password: ");
            String password= br.readLine();

            if(password.equals("user123")){
                return true;
            } else{
                System.out.println("Invalid Password.");
                if(!willDoAgain("Enter another Password?")){
                    return false;
                }
            }
        }
    }
}
