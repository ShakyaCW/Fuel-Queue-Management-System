package javaFxTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class FuelQueue {

    List<Passenger> queue = new ArrayList<>();
    static Passenger[] waitingList = new Passenger[5];

    static int front = -1;
    static int rear = -1;
    private int size;
    static double fuelStock = 6600;
    private int income;

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
            "111 or GUI: View Graphical User Interface",
            "999 or EXT: Exit the Program.",
    };

    public static void printMenu(String[] optionList) {
        for (String option : optionList) {
            System.out.println(option);
        }
        System.out.print("Enter your choice here : ");
    }

    public void addCustomer(int queueNo) {
        if (!isFull()) {
            Scanner input = new Scanner(System.in);

            System.out.print("First Name : ");
            String firstName = input.nextLine();

            System.out.print("Last Name : ");
            String lastName = input.nextLine();

            System.out.print("Vehicle Number : ");
            String vehicleNo = input.nextLine();

            int liters;
            while (true) {
                System.out.print("Liters : ");
                try {
                    liters = input.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Enter a valid amount of liters \n");
                    input.next();
                }
            }

            fuelStock = fuelStock-liters;
            Passenger newPerson = new Passenger(firstName, lastName, vehicleNo, liters);
            queue.add(newPerson);
            System.out.println(""+newPerson.getFirstName()+" "+newPerson.getLastName()+" added to the Queue "+queueNo+" successfully.");
            size = size + 1;
        }
        else {
            if (!((rear+1)%5 == front)){
                System.out.println("All queues are Full.\nNew customer will be added to the waiting list\n");
                Scanner input = new Scanner(System.in);

                System.out.print("First Name : ");
                String firstName = input.nextLine();

                System.out.print("Last Name : ");
                String lastName = input.nextLine();

                System.out.print("Vehicle Number : ");
                String vehicleNo = input.nextLine();

                int liters;
                while (true) {
                    System.out.print("Liters : ");
                    try {
                        liters = input.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a valid amount of liters \n");
                        input.next();
                    }
                }
                fuelStock = fuelStock-liters;
                Passenger newPassenger = new Passenger(firstName, lastName, vehicleNo, liters);
                addToWaitingQueue(newPassenger);}
            else {
                System.out.println("\nWaiting List and all Fuel Queues are full.\nYou can't add more.");
            }
        }

    }

    public int lengthOfQueue () {
        return size;
    }

    public void show(int x){
        System.out.print("Queue "+x+" : ");
        for (Passenger newPassenger : queue){
            System.out.print(newPassenger.getFirstName() + " " + newPassenger.getLastName() + ", ");
        }
        System.out.println(" ");
    }

    public boolean isFull () {
        return lengthOfQueue()==6;
    }

    public boolean notEmpty() {
        return lengthOfQueue() != 0;
    }

    public void viewEmpty (int x) {
        if (size<6) {
            System.out.print("Queue "+x+" : ");
            System.out.println(""+(6-size)+" places remaining.");
        }
    }

    public void removeCustomer(int i, int queueNo){
        if (notEmpty()){
            int position = 0;
            for (Passenger newPassenger : queue){
                position++;
                if (position == i) {
                    fuelStock = fuelStock + newPassenger.getLiters();
                    queue.remove(newPassenger);
                    System.out.println(""+newPassenger.getFirstName()+" "+newPassenger.getLastName()+" removed from Queue "+queueNo+" successfully.");
                    size = size-1;

                    if (!(front == -1 && rear == -1) ){
                        queue.add(waitingList[front]);
                        size = size + 1;
                        removeFromWaitingQueue();
                    }
                    break;
                }
            }}
        else {
            System.out.println("Can't remove customer\nQueue is Empty\n");
        }
    }

    public void removeServed(int queueNo){
        if (notEmpty()){
            income = income + (430*queue.get(0).getLiters());
            System.out.println(""+queue.get(0).getLiters()+" Liters served to "+queue.get(0).getFirstName()+" "+queue.get(0).getLastName()+" and removed from Queue "+queueNo+" successfully.");
            queue.remove(0);
            size = size - 1;
            if (!(front == -1 && rear == -1)){
                queue.add(waitingList[front]);
                size = size + 1;
                removeFromWaitingQueue();
            }}
        else {
            System.out.println("Can't remove customer\nQueue is Empty");
        }
    }

    public void sortAlphabetically (int n) {
        String[] sortedArray = new String[lengthOfQueue()];
        int i = 0;
        for (Passenger newPassenger : queue){
            sortedArray[i] = newPassenger.getFirstName() + " " + newPassenger.getLastName();
            i++;
        }
        for (int x =0; x<sortedArray.length; x++){
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

    public void storeData(String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        for (Passenger newPassenger : queue) {
            objOut.writeObject(newPassenger);
        }
    }

    public void loadData(String fileName) throws IOException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(fin);

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

    public static int getQueue() {
        Scanner input = new Scanner(System.in);
        int queueNO;
        while (true) {
            System.out.print("Enter the queue number you want to remove customer : ");
            try {
                queueNO = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid queue number.\n");
                input.next();
            }
        }
        return queueNO;
    }

    public static int getPosition() {
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

    public int getIncome(){
        return income;
    }

    public static int getLiters(){
        Scanner input = new Scanner(System.in);
        int liters;
        while (true) {
            System.out.print("Enter the amount to be added in Liters : ");
            try {
                liters = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please enter valid amount of liters.\n");
                input.next();
            }
        }
        return liters;
    }

    public static void addFuelStock(){
        System.out.println("\nThere are " + fuelStock + " Liters left in the stock.\nMaximum " + (6600 - fuelStock) + " Liters can be added.\n");
        int addAmount = getLiters();
        if ((addAmount+fuelStock) <= 6600){
            fuelStock = fuelStock + addAmount;
            System.out.println("\n" + addAmount + " Liters added successfully.");
            System.out.println("Remaining Fuel Stock : " + fuelStock + " Liters.\n");
        }
        else {
            System.out.println("\nPlease enter an amount less than or equals to " + (6600 - fuelStock) + "\n");
        }
    }

    public void addToWaitingQueue(Passenger newPerson){
        if (front==-1 && rear ==-1){
            front = 0;
            rear = 0;
            waitingList[rear] = newPerson;
        }
        else if ((rear+1)%5 == front ){
            System.out.println("Waiting list is full.\n");
        }
        else {
            rear = (rear+1)%5;
            waitingList[rear] = newPerson;
        }
    }

    public void removeFromWaitingQueue(){
        if ((front==-1) && (rear==-1)){
            System.out.println("\nWaiting list is empty.");
        }
        else if (front==rear){
            front = -1;
            rear = -1;
        }
        else {
            front = (front+1)%5;
        }
    }

    public static void showWaitingList() {
        if (front == -1 && rear==-1) // Check for empty queue
        {
            System.out.println("Waiting List : ");
            return;
        }
        System.out.print("Waiting List : ");

        if (rear >= front) //if rear has not crossed the size limit
        {
            for (int i = front; i <= rear; i++) //print elements using loop
            {
                System.out.print(waitingList[i].getFirstName() + " " + waitingList[i].getLastName() + ", ");
                System.out.print(" ");
            }
            System.out.println();
        } else {
            for (int i = front; i < 5; i++) {
                System.out.print(waitingList[i].getFirstName() + " " + waitingList[i].getLastName() + ", ");
                System.out.print(" ");
            }
            for (int i = 0; i <= rear; i++) // Loop for printing elements from 0th index till rear position
            {
                System.out.print(waitingList[i].getFirstName() + " " + waitingList[i].getLastName() + ", ");
                System.out.print(" ");
            }

        }
    }

    public static void saveWaitingList(String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        if (!(front == -1)) {
            if (rear >= front) //if rear has not crossed the size limit
            {
                for (int i = front; i <= rear; i++) //print elements using loop
                {
                    objOut.writeObject(waitingList[i]);
                }
            } else {
                for (int i = front; i < 5; i++) {
                    objOut.writeObject(waitingList[i]);
                }
                for (int i = 0; i <= rear; i++) // Loop for printing elements from 0th index till rear position
                {
                    objOut.writeObject(waitingList[i]);
                }
            }
        }
    }

    public static void loadWaitingList(String fileName) throws IOException {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);

        while(true) {
            try {
                Passenger newPassenger = (Passenger) objIn.readObject();
                fuelStock = fuelStock-newPassenger.getLiters();
                if (front==-1 && rear ==-1){
                    front = 0;
                    rear = 0;
                    waitingList[rear] = newPassenger;
                }
                else if ((rear+1)%5 == front ){
                    System.out.println("Waiting list is full.\n");
                }
                else {
                    rear = (rear+1)%5;
                    waitingList[rear] = newPassenger;
                }

            }
            catch (IOException | ClassNotFoundException e){
                break;
            }
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
        writer.write(Integer.toString(income));
        writer.close();
    }

    public void loadIncome(String fileName) throws IOException {
        String line;
        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
        line = bufReader.readLine();
        income = Integer.parseInt(line);
    }

    public ObservableList<Passenger> getPassenger(){
        ObservableList<Passenger> people = FXCollections.observableArrayList();
        people.addAll(queue);
        return people;
    }

    public static ObservableList<Passenger> getWaitingList() {
        ObservableList<Passenger> waitingPeople = FXCollections.observableArrayList();
        if (!(front == -1)) {
            if (rear >= front) //if rear has not crossed the size limit
            {
                waitingPeople.addAll(Arrays.asList(waitingList).subList(front, rear + 1));
            } else {
                waitingPeople.addAll(Arrays.asList(waitingList).subList(front, 5));
                waitingPeople.addAll(Arrays.asList(waitingList).subList(0, rear + 1));
            }
        }
        return waitingPeople;
    }
}
