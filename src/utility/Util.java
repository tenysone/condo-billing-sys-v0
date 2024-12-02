package utility;

import java.io.*;
import java.util.*;

public class Util {
    public static final BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    public static final Scanner sc= new Scanner(System.in);

    /*
     * Method: 'willDoAgain'
     * @param String prompt: case-dependent prompt; i.e., "Enter new something"
     * @return true or false to signal whether a process willDoAgain
     * If user puts 'Y', the method returns a 'true' value
     * If user puts 'N', the method returns a 'false' value
     * If invalid, the method would just continue on looping
     */
    public static boolean willDoAgain(String prompt) throws IOException {
        while(true){
            System.out.print(prompt + " [y/n]: ");
            String ans= br.readLine().toUpperCase();
                if(ans.equals("Y")){
                    return true;
                } else if(ans.equals("N")){
                    return false;
                }else{
                    System.out.println("Invalid input. Enter only 'Y' or 'N'.");
                }
        }
    }

    public static String getNonEmpty(String prompt) throws IOException {
        while(true){
            System.out.print(prompt + " ");
            String input= br.readLine();
            if(input.isEmpty()){
                System.out.println("Input must not be empty. Please try again.");
            } else{
                return input;
            }
        }
    }

    public static String getNewValue(String prompt) throws IOException {
        System.out.print(prompt + "? ");
        String yn= br.readLine().toUpperCase();
        if(yn.equals("Y")){
            System.out.print(prompt + ": ");
            return br.readLine().toUpperCase();
        } else if (yn.equals("N")){
            return "";
        } else{
            System.out.println("Invalid input. Please try again.");
            return "";
        }
    }

    public static boolean isValidDate(int a, int b, int c){
        boolean valid= false;
        if(a >= 2020 && a <= 2025){
            if(b >= 1 && b <= 12){
                if(c >= 1 && c <= 31){
                    valid= true;
                } else{
                    System.out.println("Day must be '1' to '31'");
                }
            } else{
                System.out.println("Month must be '1' to '12'");
            }
        } else{
            System.out.println("Year must be '2020' to '2025'");
        }
        return valid;
    }

    public static void addBorder(){
        System.out.println("------------------------------" +
                "---------------------------------------------------------------" +
                "---------------------------------------------------------------" +
                "---------------------------------------------------------------" +
                "-------------------------------------------------");
    }
    public static void addDoubleBorder(){
        System.out.println("====================================================" +
                "===============================================================" +
                "===============================================================" +
                "===============================================================" +
                "===========================");
    }
}
