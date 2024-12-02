package main;

import storage.DBManager;
import storage.UnitDB;
import utility.UserFunctions;

import java.io.IOException;

import static utility.Util.*;

public class Main {
    protected static final DBManager dbManager= new DBManager();
    private static final AdminUI admin= new AdminUI();
    private static final OwnerUI owner= new OwnerUI();

    /* - Log-in Page of the System
    *  - 'User/Admin Log-in' serves as the entry point to main functions
    *  - 'Exit' terminates the program itself, simulating a log-out process
    *  - */
    public static void main(String[] args) throws IOException {
        DBManager.addDefault();
        while(true){
            System.out.println("""
                    1.Log-in as Homeowner
                    2.Log-in as Admin
                    0.Exit
                    """);
            String choice= getNonEmpty("What would you like to do?");
            if(choice.equals("1")){
                owner.loginPage();
            } else if(choice.equals("2")){
                admin.loginPage();
            } else if(choice.equals("0")){
                System.out.println("Logging-out...");
                break;
            } else{
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}