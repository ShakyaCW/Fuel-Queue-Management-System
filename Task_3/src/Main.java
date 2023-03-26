import java.io.IOException;
import java.util.Scanner;

public class Main {

    static FuelQueue queue1 = new FuelQueue();
    static FuelQueue queue2 = new FuelQueue();
    static FuelQueue queue3 = new FuelQueue();
    static FuelQueue queue4 = new FuelQueue();
    static FuelQueue queue5 = new FuelQueue();


    public static void main(String[] args) throws IOException {

        String choice = "1";
        while (!choice.equals("999") && !choice.equals("EXT")) {

            FuelQueue.printMenu(FuelQueue.optionList);
            Scanner userInput = new Scanner(System.in);
            choice = userInput.next().toUpperCase();
            switch (choice) {
                case "100", "VFQ" -> {
                    System.out.println(" ");
                    queue1.show(1);
                    queue2.show(2);
                    queue3.show(3);
                    queue4.show(4);
                    queue5.show(5);
//                    waiting.showWaitingList();
                    FuelQueue.showWaitingList();
                    System.out.println(" ");}
                case "101", "VEQ" -> {
                    System.out.println(" ");
                    queue1.viewEmpty(1);
                    queue2.viewEmpty(2);
                    queue3.viewEmpty(3);
                    queue4.viewEmpty(4);
                    queue5.viewEmpty(5);
                    System.out.println(" ");
                }
                case "102", "ACQ" -> {
                    System.out.println(" ");
                    int one = queue1.lengthOfQueue();
                    int two = queue2.lengthOfQueue();
                    int three = queue3.lengthOfQueue();
                    int four = queue4.lengthOfQueue();
                    int five = queue5.lengthOfQueue();
                    int[] array = {one,two,three,four,five};
                    for(int i = 0; i<5; i++ ){
                        for(int j = i+1; j<5; j++){
                            if(array[i]>array[j]){
                                int temp = array[i];
                                array[i] = array[j];
                                array[j] = temp;}}}
                    if (FuelQueue.fuelStock <= 500){
                        System.out.println("WARNING !!!\nOnly "+FuelQueue.fuelStock+" Liters left in the stock\n");
                    }
                    if (array[0]==one){queue1.addCustomer(1);}
                    else if (array[0]==two) {queue2.addCustomer(2);}
                    else if (array[0]==three) {queue3.addCustomer(3);}
                    else if (array[0]==four) {queue4.addCustomer(4);}
                    else if (array[0]==five) {queue5.addCustomer(5);}
                    System.out.println("Remaining Fuel Stock : "+FuelQueue.fuelStock+" Liters");
                    System.out.println(" ");
                }
                case "103", "RCQ" -> {
                    System.out.println(" ");
                    int queueNo = FuelQueue.getQueue();
                    int position = FuelQueue.getPosition();

                    switch (queueNo) {
                        case 1 ->{ if(queue1.lengthOfQueue()>=position && position>0){
                            queue1.removeCustomer(position, 1);
                        }else {
                            System.out.println("Invalid Position.\nThere are only "+queue1.lengthOfQueue()+" passengers in Queue 1");
                        }}

                        case 2 ->{if (queue2.lengthOfQueue()>=position && position>0){
                            queue2.removeCustomer(position, 2);
                        }else {
                            System.out.println("Invalid Position.\nThere are only "+queue2.lengthOfQueue()+" passengers in Queue 2");
                        }}

                        case 3 -> { if (queue3.lengthOfQueue()>=position && position>0){
                            queue3.removeCustomer(position, 3);
                        } else {
                            System.out.println("Invalid Position.\nThere are only "+queue3.lengthOfQueue()+" passengers in Queue 3");
                        }}

                        case 4 -> { if (queue4.lengthOfQueue()>=position && position>0){
                            queue4.removeCustomer(position, 4);
                        } else {
                            System.out.println("Invalid Position\nThere are only "+queue4.lengthOfQueue()+" passengers in Queue 4");
                        }}

                        case 5 -> { if (queue5.lengthOfQueue()>=position && position>0){
                            queue5.removeCustomer(position, 5);
                        } else {
                            System.out.println("Invalid Position\nThere are only "+queue5.lengthOfQueue()+" passengers in Queue 5");
                        }}
                    }
                    System.out.println(" ");
                }
                case "104", "PCQ" -> {
                    System.out.println(" ");

                    int queueNumber = FuelQueue.getQueue();
                    switch (queueNumber){
                        case 1 -> queue1.removeServed(1);
                        case 2 -> queue2.removeServed(2);
                        case 3 -> queue3.removeServed(3);
                        case 4 -> queue4.removeServed(4);
                        case 5 -> queue5.removeServed(5);
                    }
                    System.out.println(" ");
                }
                case "105", "VCS" -> {
                    System.out.println(" ");
                    queue1.sortAlphabetically(1);
                    queue2.sortAlphabetically(2);
                    queue3.sortAlphabetically(3);
                    queue4.sortAlphabetically(4);
                    queue5.sortAlphabetically(5);
                    System.out.println(" ");
                }
                case "106", "SPD" -> {
                    queue1.storeData("queue1.txt");
                    queue2.storeData("queue2.txt");
                    queue3.storeData("queue3.txt");
                    queue4.storeData("queue4.txt");
                    queue5.storeData("queue5.txt");
                    FuelQueue.saveWaitingList("waitingList.txt");
                    FuelQueue.storeFuelStock();
                    queue1.storeIncome("queue1Income.txt");
                    queue2.storeIncome("queue2Income.txt");
                    queue3.storeIncome("queue3Income.txt");
                    queue4.storeIncome("queue4Income.txt");
                    queue5.storeIncome("queue5Income.txt");
                    System.out.println("\nData Stored successfully.\n");
                }
                case "107", "LPD" -> {
                    try {
                    queue1.loadData("queue1.txt");
                    queue2.loadData("queue2.txt");
                    queue3.loadData("queue3.txt");
                    queue4.loadData("queue4.txt");
                    queue5.loadData("queue5.txt");
                    FuelQueue.loadWaitingList("waitingList.txt");
                    FuelQueue.loadFuelStock();
                    queue1.loadIncome("queue1Income.txt");
                    queue2.loadIncome("queue2Income.txt");
                    queue3.loadIncome("queue3Income.txt");
                    queue4.loadIncome("queue4Income.txt");
                    queue5.loadIncome("queue5Income.txt");
                    System.out.println("\nData loaded successfully.\n");}
                    catch (Exception e) {
                        System.out.println("\nNo saved data to be loaded.\n");
                    }
                }
                case "108", "STK" -> System.out.println("\nRemaining Fuel Stock : "+FuelQueue.fuelStock+" Liters\n");
                case "109", "AFS" -> FuelQueue.addFuelStock();
                case "110", "IFQ" -> {
                    System.out.println("\nQueue 1 Income : Rs. "+queue1.getIncome()+"");
                    System.out.println("Queue 2 Income : Rs. "+queue2.getIncome()+"");
                    System.out.println("Queue 3 Income : Rs. "+queue3.getIncome()+"");
                    System.out.println("Queue 4 Income : Rs. "+queue4.getIncome()+"");
                    System.out.println("Queue 5 Income : Rs. "+queue5.getIncome()+"\n");
                }
                case "999", "EXT" -> System.out.println("\nExit the program");
                default -> System.out.println("\nPlease enter a valid input. \n");
            }

        }

    }
}