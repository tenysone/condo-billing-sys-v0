package main;

import storage.DBManager;
import utility.UserFunctions;

import java.io.IOException;

import static main.Main.dbManager;
import static utility.Util.*;

public class AdminUI implements UserFunctions {
    /*
    *   Method: 'mainPage'
    *   - Contains all admin-related functions
    *   @param String:welcomeName to be used as a welcome header upon entry
    *   @return none
    */
    @Override
    public void mainPage(String welcomeName) throws IOException {
        while(true){
            addDoubleBorder();
            System.out.printf("%90s %n","Welcome " + welcomeName);
            addDoubleBorder();

            System.out.println("""
                    1.Units
                    2.Owners
                    3.Bills
                    0.Log-out
                    """);
            String choice= getNonEmpty("What would you like to do?");

            switch(choice){
                case "1"-> {
                    units();
                }
                case "2"-> {
                    owners();
                }
                case "3"-> {
                    dues();
                }
                case "0"-> {
                    System.out.println("Logging-out...");
                    addDoubleBorder();
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
            addDoubleBorder();
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
                    mainPage("ADMIN");
                    return;
                } else{
                    if(willDoAgain("Terminate Admin Log-in Process?")){
                        System.out.println("Going back to Home Page...");
                        return;
                    }
                }
            } else{
                if(willDoAgain("Terminate Admin Log-in Process?")){
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

            if(username.equals("AXIS_ADMIN")){
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

            if(password.equals("admin123")){
                return true;
            } else{
                System.out.println("Invalid Password.");
                if(!willDoAgain("Enter another Password?")){
                    return false;
                }
            }
        }
    }

    /*
    *   Methods
    *   - Contains specific functions related to key areas
    *   @param none
    *   @return none
    */
    public void units() throws IOException {
        while(true) {
            System.out.println("""
                    1.View All Units
                    2.Add New Units
                    0.Back to Home Page
                    """);
            String choice = getNonEmpty("What would you like to do?");

            switch (choice) {
                case "1" -> {
                    dbManager.displayUnit();
                }
                case "2" -> {
                    dbManager.addUnit();
                }
                case "0" -> {
                    System.out.println("Going back to Home Page...");
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public void owners() throws IOException {
        while(true) {
            System.out.println("""
                    1.View All Owners
                    2.Add New Owners
                    3.Register Units
                    0.Back to Home Page
                    """);
            String choice = getNonEmpty("What would you like to do?");

            switch (choice) {
                case "1" -> {
                    dbManager.displayOwner();
                }
                case "2" -> {
                    dbManager.addOwner();
                }
                case "3" -> {
                    String filler1= "", filler2= "";
                    dbManager.registerUnits(filler1,filler2);
                }
                case "0" -> {
                    System.out.println("Going back to Home Page...");
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void dues() throws IOException {
        while(true) {
            System.out.println("""
                    1.View All Bills
                    2.Issue New Bills
                    3.Issue Penalties
                    0.Back to Home Page
                    """);
            String choice = getNonEmpty("What would you like to do?");

            switch (choice) {
                case "1" -> {
                    dbManager.displayPaymentRecord();
                }
                case "2" -> {
                    dbManager.issueDue();
                }
                case "3" -> {
                    String filler= "";
                    dbManager.issuePenalty(filler);
                }
                case "0" -> {
                    System.out.println("Going back to Home Page...");
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
