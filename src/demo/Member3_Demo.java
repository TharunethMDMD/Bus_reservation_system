import java.util.ArrayList;
import java.util.Scanner;


class Bus {

    int busID;
    String busName;
    String destination;
    double fare;

    Bus left;
    Bus right;


    public Bus(int busID, String busName, String destination, double fare) {

        this.busID = busID;
        this.busName = busName;
        this.destination = destination;
        this.fare = fare;

        left = null;
        right = null;
    }
}

class BST {
    Bus root;

    public void insert(int id, String name, String destination, double fare) {
        root = insertRec(root, id, name, destination, fare);

    }

    private Bus insertRec(Bus root, int id, String name, String destination, double fare) {

        if(root == null) {
            return new Bus(id, name, destination, fare);

        }
        if(id < root.busID) {
            root.left = insertRec(root.left, id, name, destination, fare);

        }
        else if(id > root.busID) {
            root.right = insertRec(root.right, id, name, destination, fare);
        }
        return root;
    }

    public Bus search(Bus root, int id) {

        if(root == null || root.busID == id) {
            return root;
        }
        if(id < root.busID) {
            return search(root.left, id);
        }
        else {
            return search(root.right, id);
        }
    }

    public void inorder(Bus root) {

        if(root != null) {
            inorder(root.left);

            System.out.println("Bus ID      : " + root.busID);
            System.out.println("Bus Name    : " + root.busName);
            System.out.println("Destination : " + root.destination);
            System.out.println("Fare        : " + root.fare);
            System.out.println("-----------------------------");

            inorder(root.right);

        }
    }

    public void store(Bus root, ArrayList<Bus> list) {

        if(root != null) {

            store(root.left, list);
            list.add(root);
            store(root.right, list);

        }
    }
}

class SelectionSort {

    public static void sortByFare(ArrayList<Bus> buses) {
        int n = buses.size();

        for(int i = 0; i < n-1; i++) {
            int min = i;

            for(int j = i+1; j < n; j++) {

                if(buses.get(j).fare < buses.get(min).fare) {
                    min = j;

                }
            }
            Bus temp = buses.get(i);
            buses.set(i, buses.get(min));
            buses.set(min, temp);
        }
    }
}

public class BusReservationSystem {

    public static void main(String[] args) {

        BST tree = new BST();
        Scanner sc = new Scanner(System.in);

        tree.insert(105, "Express", "Colombo", 1200);
        tree.insert(102, "Super Line", "Kandy", 900);
        tree.insert(110, "Luxury", "Galle", 1500);
        tree.insert(101, "City Bus", "Matara", 800);
        tree.insert(108, "Highway", "Jaffna", 1300);

        int choice;

        do {

            System.out.println("\n===== BUS RESERVATION SYSTEM =====");
            System.out.println("1. Display All Buses");
            System.out.println("2. Search Bus");
            System.out.println("3. Sort Buses By Fare");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    System.out.println("\n===== ALL BUS DETAILS =====");

                    tree.inorder(tree.root);

                    break;

                case 2:
                    System.out.print("\nEnter Bus ID to Search: ");

                    int id = sc.nextInt();
                    Bus result = tree.search(tree.root, id);

                    if(result != null) {
                        System.out.println("\n===== BUS FOUND =====");
                        System.out.println("Bus ID      : " + result.busID);
                        System.out.println("Bus Name    : " + result.busName);
                        System.out.println("Destination : " + result.destination);
                        System.out.println("Fare        : " + result.fare);


                    }
                    else {
                        System.out.println("Bus Not Found!");

                    }
                    break;

                case 3:
                    ArrayList<Bus> list = new ArrayList<>();
                    tree.store(tree.root, list);
                    SelectionSort.sortByFare(list);

                    System.out.println("\n===== SORTED BY FARE =====");

                    for(Bus b : list) {

                        System.out.println(

                                b.busID + " | " +

                                        b.busName + " | " +

                                        b.destination +

                                        " | Fare: " + b.fare

                        );

                    }
                    break;

                case 4:
                    System.out.println("System Closed.");

                    break;

                default:
                    System.out.println("Invalid Choice!");

            }
        }while(choice != 4);
        sc.close();
    }
}