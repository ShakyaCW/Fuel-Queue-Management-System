import java.io.*;
import java.util.*;

public class FuelQueue {

    List<Passenger> queue = new ArrayList<>(); // Initialization of the array of objects that used as the queue.

    private int size; // Declaration of queue size.
    static double fuelStock = 6600;
    private double income;

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
            "110 or IFQ: Print Income.",
            "999 or EXT: Exit the Program.",
    };

    public static void printMenu(String[] optionList) { // Method for printing option list.
        for (String option : optionList) {
            System.out.println(option);
        }
        System.out.print("Enter your choice here : ");
    }

    public void addCustomer(int queueNo) { // 102 or ACQ: Add customer to a Queue.
        if (!isFull()) { // Checking whether the queue is full or not.
            Scanner input = new Scanner(System.in);

            System.out.print("First Name : ");
            String firstName = input.nextLine();

            System.out.print("Last Name : ");
            String lastName = input.nextLine();

            System.out.print("Vehicle Number : ");
            String vehicleNo = input.nextLine();

            double liters;
            while (true) { // Validating user input.
                System.out.print("Liters : ");
                try {
                    liters = input.nextDouble();
                    break;
                } catch (Exception e) {
                    System.out.println("Enter a valid amount of liters \n");
                    input.next();
                }
            }

            fuelStock = fuelStock-liters; // Updating fuel stock according to the user input.
            Passenger newPerson = new Passenger(firstName, lastName, vehicleNo, liters); // Creating new Passenger object.
            queue.add(newPerson); // Adding created object to the queue.
            System.out.println(""+newPerson.getFirstName()+" "+newPerson.getLastName()+" added to the Queue "+queueNo+" successfully.");
            size = size + 1; // Increasing size of the queue after adding new object.
        }
        else {
            System.out.println("All queues are full\nYou can't add more.\n");
        }

    }

    public int lengthOfQueue () {
        return size;
    } // This method will return the length of the queue.

    public void show(int x){ // 100 or VFQ: View all Fuel Queues.
        System.out.print("Queue "+x+" : ");
        for (Passenger newPassenger : queue){
            System.out.print(newPassenger.getFirstName() + " " + newPassenger.getLastName() + ", ");
        }
        System.out.println(" ");
    }

    public boolean isFull () {
        return lengthOfQueue() == 6;
    } // This method returns true if the queue is full.

    public boolean notEmpty() {
        return lengthOfQueue() != 0;
    } // This method returns true if the queue is not empty.

    public void viewEmpty (int x) { // 101 or VEQ: View all Empty Queues
        if (size<6) {
            System.out.print("Queue "+x+" : ");
            System.out.println(""+(6-size)+" places remaining.");
        }
    }

    public void removeCustomer(int i, int queueNo){ // 103 or RCQ: Remove a customer from a Queue. (From a specific location)
        if (notEmpty()){
            int position = 0;
            for (Passenger newPassenger : queue){
                position++;
                if (position == i) {
                    fuelStock = fuelStock + newPassenger.getLiters();
                    queue.remove(newPassenger);
                    System.out.println(""+newPassenger.getFirstName()+" "+newPassenger.getLastName()+" removed from Queue "+queueNo+" successfully.");
                    size = size-1;
                    break;
                }
            }}
        else {
            System.out.println("Can't remove customer.\nQueue is Empty\n");
        }
    }

    public void removeServed(int queueNo){
        if (notEmpty()){
            income = income + (430*queue.get(0).getLiters());
            System.out.println(""+queue.get(0).getLiters()+" Liters served to "+queue.get(0).getFirstName()+" "+queue.get(0).getLastName()+" and removed from Queue "+queueNo+" successfully.");
            queue.remove(0);
            size = size - 1;
            }
        else {
            System.out.println("Can't remove customer.\nQueue is Empty.");
        }
    }

    public void sortAlphabetically (int n) {
        String[] sortedArray = new String[lengthOfQueue()];
        int i = 0;
        for (Passenger newPassenger : queue){ // Adding all elements in the queue to the new array.
            sortedArray[i] = newPassenger.getFirstName() + " " + newPassenger.getLastName();
            i++;
        }
        for (int x =0; x<sortedArray.length; x++){ // Bubble sort
            for (int k=x+1; k<sortedArray.length; k++){
                if (sortedArray[x].compareToIgnoreCase(sortedArray[k]) > 0){
                    String temp = sortedArray[x];
                    sortedArray[x] = sortedArray[k];
                    sortedArray[k] = temp;
                }
            }
        }
        System.out.print("Queue "+n+" : ");
        for (String name : sortedArray) {
            System.out.print(name + ", ");
        }
        System.out.println(" ");
    }

    public void storeData(String fileName) throws IOException { // Reference : DS Academy YouTube Channel.
        File file = new File(fileName); // Creating new file.
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        for (Passenger newPassenger : queue) { // Writing all objects in the queue to the text file.
            objOut.writeObject(newPassenger);
        }
    }

    public void loadData(String fileName) throws IOException { // Reference : DS Academy YouTube Channel.
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(fin);
        queue.clear();

        while(true) {
            try {
                Passenger newPassenger = (Passenger) objIn.readObject();
                queue.add(newPassenger);
                size = size + 1;
            }
            catch (IOException | ClassNotFoundException e){
                break;
            }
        }

    }

    public static int getQueue() { // Validating user input
        Scanner input = new Scanner(System.in);
        int queueNO;
        while (true) {
            System.out.print("Enter the queue number you want to remove customer : ");
            try {
                queueNO = input.nextInt();
                if (queueNO<1 || queueNO>5) {
                    System.out.println("Please enter a valid queue number.\n");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid queue number.\n");
                input.next();
            }
        }
        return queueNO;
    }

    public static int getPosition() { // Validating user input.
        Scanner input = new Scanner(System.in);
        int position;
        while (true) {
            System.out.print("Enter the position of the customer : ");
            try {
                position = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid position.\n");
                input.next();
            }
        }
        return position;
    }

    public double getIncome() {
        return income;
    }

    public static double getLiters(){
        Scanner input = new Scanner(System.in);
        double liters;
        while (true) {
            System.out.print("Enter the amount to be added in Liters : ");
            try {
                liters = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Please enter valid amount of liters.\n");
                input.next();
            }
        }
        return liters;
    }

    public static void addFuelStock(){ // 109 or AFS: Add Fuel Stock
        System.out.println("\nThere are " + fuelStock + " Liters left in the stock.\nMaximum " + (6600 - fuelStock) + " Liters can be added.\n");
        double addAmount = getLiters();
        if ((addAmount+fuelStock) <= 6600){
            fuelStock = fuelStock + addAmount;
            System.out.println("\n" + addAmount + " Liters added successfully.");
            System.out.println("Remaining Fuel Stock : " + fuelStock + " Liters.\n");
        }
        else {
            System.out.println("\nPlease enter an amount less than or equals to " + (6600 - fuelStock) + "\n");
        }
    }

    public static void storeFuelStock() throws IOException {
        FileWriter writer = new FileWriter("fuelStock.txt");
        writer.write(Double.toString(fuelStock));
        writer.close();
    }

    public static void loadFuelStock() throws IOException {
        String line;
        BufferedReader bufReader = new BufferedReader(new FileReader("fuelStock.txt"));
        line = bufReader.readLine();
        fuelStock = Double.parseDouble(line);
    }

    public void storeIncome(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(Double.toString(income));
        writer.close();
    }

    public void loadIncome(String fileName) throws IOException {
        String line;
        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
        line = bufReader.readLine();
        income = Double.parseDouble(line);
    }

}
