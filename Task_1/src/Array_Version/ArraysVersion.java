package Array_Version;

import java.io.*;
import java.util.*;

public class ArraysVersion {
    static String[] pump1 = new String[6];
    static String[] pump2 = new String[6];
    static String[] pump3 = new String[6];
    static Double fuelStock = 6600.0;
    static String[][] pumpArray = new String[3][6];


    static String[] optionList = {
            "100 or VFQ: View all Fuel Queues.",
            "101 or VEQ: View all Empty Queues.",
            "102 or ACQ: Add customer to a Queue.",
            "103 or RCQ: Remove a customer from a Queue.",
            "104 or PCQ: Remove a served customer.",
            "105 or VCS: View Customers Sorted in alphabetical order.",
            "106 or SPD: Store Program Data into file.",
            "107 or LPD: Load Program Data from file.",
            "108 or STK: View Remaining Fuel Stock.",
            "109 or AFS: Add Fuel Stock.",
            "999 or EXT: Exit the Program.",
    };

    public static void main(String[] args) throws IOException {
        // Adding three pump arrays to 2D Array.
        pumpArray[0] = pump1;
        pumpArray[1] = pump2;
        pumpArray[2] = pump3;

        String choice = "1";
        while (!choice.equals("999") && !choice.equals("EXT")) {
            printMenu(optionList);
            Scanner userInput = new Scanner(System.in);

            choice = userInput.next().toUpperCase();
            switch (choice) {
                case "100", "VFQ" -> viewAll();
                case "101", "VEQ" -> viewEmpty();
                case "102", "ACQ" -> addCustomer();
                case "103", "RCQ" -> removeCustomer();
                case "104", "PCQ" -> removeServed();
                case "105", "VCS" -> sortAlphabetically();
                case "106", "SPD" -> storeData();
                case "107", "LPD" -> loadData();
                case "108", "STK" -> remainingFuelStock();
                case "109", "AFS" -> addFuelStock();
                case "999", "EXT" -> System.out.println("\n Exit the program");
                default -> System.out.println("\n Please enter a valid input. \n");
            }

        }

    }

    public static void printMenu(String[] optionList) {
        for (String option : optionList) {
            System.out.println(option);
        }
        System.out.print("Enter your choice here : ");
    }

    // Methods

    // View all Fuel Queues.
    private static void viewAll() {
        System.out.println(" ");

        int pumpNumber = 1;
        for (String[] smallArray : pumpArray) {
            System.out.print("Pump " + pumpNumber + " : [");
            pumpNumber++;
            int nameCount = 0;
            for (String name : smallArray) {
                if (name != null) {
                    System.out.print(name + ",");
                    nameCount = nameCount + 1;
                }
            }
            System.out.print("]");
            int remaining1 = 6 - nameCount;
            System.out.println("  " + remaining1 + " Places Remaining");
        }

        System.out.println(" ");
    }

    // View all Empty Queues.
    private static void viewEmpty() {
        System.out.println(" ");
        int pumpNumber = 1;
        for (String[] smallArray : pumpArray) {
            int nameCount = 0;
            for (String name : smallArray) {
                if (name != null) {
                    nameCount++;
                }
            }
            if (nameCount == 0) {
                System.out.println("Pump " + pumpNumber + " : Empty");
            }
            else if (nameCount>0 && nameCount<6){
                System.out.println("Pump "+pumpNumber+" : "+(6-nameCount)+" Places Available.");
            }
            pumpNumber++;
        }
        System.out.println(" ");
    }

    // Add customer to a Queue.
    private static void addCustomer() {
        System.out.println(" ");
        Scanner userInput2 = new Scanner(System.in);

        int pump;
        loop:
        while (true) {
            if (fuelStock <= 500) {
                System.out.println("Warning !!! ");
                System.out.println("Only " + fuelStock + " Liters left in the stock.\n");
            }

            while (true){
                System.out.println("Go back to main menu : Enter 4");
                System.out.print("Enter the pump number you want to add customer : ");
            try {
                pump = userInput2.nextInt();
            } catch (Exception e) {
                System.out.println("Enter a valid pump number\n");
                break loop;
            }
            if (pump > 4 || pump <= 0) {
                System.out.println("Enter 1 for pump 1\nEnter 2 for pump 2\nEnter 3 for pump 3\n");
            }
            else {
                break;
            }
        }

        if (pump==4){
            System.out.println(" ");
            break;
        }

            System.out.print("Enter name : ");
            String nameOfCustomer = userInput2.next();

            int nameCount = 0;
            for (String name : pumpArray[pump - 1]) {
                if (name == null) {
                    pumpArray[pump - 1][nameCount] = nameOfCustomer;
                    fuelStock = fuelStock - 10;
                    System.out.println("" + nameOfCustomer + " added to the Queue " + pump + " successfully.");
                    break;
                }

                if (nameCount == 5) {
                    System.out.println("\nCan't add the customer to queue " + pump + ".\nQueue " + pump + " is Full");
                }
                nameCount++;

            }

        System.out.println(" ");
    }}

    // Remove a customer from a Queue. (From a specific location)
    private static void removeCustomer() {
        System.out.println(" ");
        Scanner userInput2 = new Scanner(System.in);

        int pump = 5;
        while (pump != 4 && pump != 1 && pump != 2 && pump != 3) {
            System.out.println("Go back to main menu : Enter 4");
            System.out.print("Enter the pump number you want to remove customer : ");
            try {
                pump = userInput2.nextInt();
            } catch (Exception e) {
                System.out.println("Enter a valid pump number");
                userInput2.next();
            }
            if (pump > 4 || pump <= 0) {
                System.out.println("Enter 1 for pump 1\nEnter 2 for pump 2\nEnter 3 for pump 3\n");
            }
        }

        if (pump != 4) {
            boolean empty = true;
            for (String name : pumpArray[pump - 1]) {
                if (name != null) {
                    empty = false;
                    break;
                }
            }
            int nameCount = 0;
            for (String name : pumpArray[pump - 1]) {
                if (name == null) {
                    break;
                }
                nameCount++;
            }
            if (!empty) {
                System.out.print("Enter the position of customer (1,2,3,4,5,6) : ");
                try {
                    int position = userInput2.nextInt();
                    if (position < 0 || position > nameCount) {
                        System.out.println("\nPlease enter a valid position.\nThere are only " + nameCount + " customers in Queue " + pump + "");
                    } else {
                        String[] newArray = new String[(pumpArray[pump - 1].length)];
                        for (int i = 0, k = 0; i < (pumpArray[pump - 1].length); i++) {
                            if (i == position - 1) {
                                continue;
                            }
                            newArray[k++] = pumpArray[pump - 1][i];
                        }

                        pumpArray[pump - 1] = newArray;
                        System.out.println("Customer at position "+position+" removed from Queue "+pump+" successfully.");
                        fuelStock = fuelStock + 10;
                    }

                } catch (Exception e) {
                    System.out.println("\nPlease enter only Integer.");
                }
            } else {
                System.out.println("\nQueue " + pump + " is Empty.");
            }

        }
        System.out.println(" ");
    }

    // Remove a served customer.
    private static void removeServed() {
        System.out.println(" ");
        Scanner userInput2 = new Scanner(System.in);
        int pump = 5;
        while (pump != 4 && pump != 1 && pump != 2 && pump != 3) {
            System.out.println("Go back to main menu : Enter 4");
            System.out.print("Enter the pump number you want to remove customer : ");
            try {
                pump = userInput2.nextInt();
            } catch (Exception e) {
                System.out.println("Enter a valid pump number");
                userInput2.next();
            }
            if (pump > 4 || pump <= 0) {
                System.out.println("Enter 1 for pump 1\nEnter 2 for pump 2\nEnter 3 for pump 3\n");
            }
        }

        if (pump != 4) {
            boolean empty = true;
            for (String name : pumpArray[pump - 1]) {
                if (name != null) {
                    empty = false;
                    break;
                }
            }
            if (!empty) {
                String[] newArray = new String[(pumpArray[pump - 1].length) - 1];
                for (int i = 0, k = 0; i < (pumpArray[pump - 1].length); i++) {
                    if (i == 0) {
                        continue;
                    }
                    newArray[k++] = pumpArray[pump - 1][i];
                }
                pumpArray[pump - 1] = newArray;
                System.out.println("Served customer removed successfully.");
            } else {
                System.out.println("Queue " + pump + " is Empty.");
            }
        }
        System.out.println(" ");
    }

    // View Customers Sorted in alphabetical order.
    private static void sortAlphabetically() {
        System.out.println(" ");
        int pumpNum = 1;
        for (String[] smallArray : pumpArray) {
            int nameCount = 0;
            for (String name : smallArray) {
                if (name == null) {
                    break;
                }
                nameCount++;
            }

            String[] sortedArray = new String[nameCount];
            System.arraycopy(smallArray, 0, sortedArray, 0, nameCount);
            for (int i = 0; i < sortedArray.length; i++) {
                for (int j = i + 1; j < sortedArray.length; j++) {
                    if (sortedArray[i].compareToIgnoreCase(sortedArray[j]) > 0) {
                        String temp = sortedArray[i];
                        sortedArray[i] = sortedArray[j];
                        sortedArray[j] = temp;
                    }
                }
            }

            System.out.print("Pump " + pumpNum + " : [");
            pumpNum++;
            for (String name : sortedArray) {
                System.out.print(name + ",");
            }
            System.out.print("]\n");
        }

        System.out.println(" ");
    }

    // View Remaining Fuel Stock.
    private static void remainingFuelStock() {
        System.out.println("\n Remaining Fuel Stock : " + fuelStock + " Liters. \n");
    }

    // Add Fuel Stock.
    private static void addFuelStock() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("\nThere are " + fuelStock + " Liters left in the stock.\nMaximum " + (6600 - fuelStock) + " Liters can be added.\n");
        try {
        System.out.print("Enter the amount to be added in Liters : ");
        double addAmount = userInput.nextDouble();
        if ((fuelStock + addAmount) <= 6600) {
            fuelStock = fuelStock + addAmount;
            System.out.println("\n" + addAmount + " Liters added successfully.");
            System.out.println("Remaining Fuel Stock : " + fuelStock + " Liters.\n");
        } else {
            System.out.println("\nPlease enter an amount less than or equals to " + (6600 - fuelStock) + "\n");
        }
    }
        catch (Exception e) {
            System.out.println("Please enter the amount in numbers.\n");
        }
    }

    // Store Program Data into file.
    private static void storeData() throws IOException {
        System.out.println(" ");

        FileWriter writer = new FileWriter("store.txt");
        for (int i = 0; i<3; i++) {
        for (String str : pumpArray[i]) {
            writer.write(str + ",");
        }
        writer.write(System.lineSeparator());}

        writer.write(fuelStock.toString());
        writer.close();
        System.out.println("Data stored successfully.");
        System.out.println(" ");
    }

    // Load Program Data from file.
    private static void loadData() {
        System.out.println(" ");
        try {
        String line;
        BufferedReader bufReader = new BufferedReader(new FileReader("store.txt"));

        for (int x =0; x<3; x++){
            line = bufReader.readLine();
            String[] values = line.split(",");
        int i = 0;
        for (String name : values) {

            if (Objects.equals(name, "null")) {
                continue;
            }
            pumpArray[x][i] = name;
            i++;
        }}

            line = bufReader.readLine();
            fuelStock = Double.parseDouble(line);
            System.out.println("Data loaded successfully.");
            System.out.println(" ");
        }
        catch (Exception e){
            System.out.println("There are no saved data to load.\n");
        }
    }

}

