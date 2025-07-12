import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodOrderingSystem {
    static List<String> orderedItems = new ArrayList<>();
    static List<Integer> orderedQuantities = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu
        System.out.println("Food Ordering System");
        System.out.println("---------------------");
        System.out.println("1. View Menu");
        System.out.println("2. Order Food");
        System.out.println("3. Exit");

        while (true) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    orderFood(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Display ordered items after each operation
            displayOrderSummary();
        }
    }

    private static void viewMenu() {
        System.out.println("Menu");
        System.out.println("----");
        System.out.println("1. Pizza ($10)");
        System.out.println("2. Burger ($8)");
        System.out.println("3. Sandwich ($6)");
        System.out.println("4. Salad ($5)");
    }

    private static void orderFood(Scanner scanner) {
        System.out.print("Enter food number: ");
        int foodNumber = scanner.nextInt();

        if (foodNumber < 1 || foodNumber > 4) {
            System.out.println("Invalid food number.");
            return;  // Return early if invalid food number
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        String foodItem = getFoodName(foodNumber);
        orderedItems.add(foodItem);
        orderedQuantities.add(quantity);

        System.out.println(foodItem + " ordered! Quantity: " + quantity);
        System.out.println("Total: $" + calculateTotal(foodNumber, quantity));
    }

    private static String getFoodName(int foodNumber) {
        switch (foodNumber) {
            case 1: return "Pizza";
            case 2: return "Burger";
            case 3: return "Sandwich";
            case 4: return "Salad";
            default: return "Unknown";
        }
    }

    private static double calculateTotal(int foodNumber, int quantity) {
        double price = 0;

        switch (foodNumber) {
            case 1:
                price = 10;
                break;
            case 2:
                price = 8;
                break;
            case 3:
                price = 6;
                break;
            case 4:
                price = 5;
                break;
        }

        return price * quantity;
    }

    private static void displayOrderSummary() {
        System.out.println("Order Summary");
        System.out.println("--------------");

        if (orderedItems.isEmpty()) {
            System.out.println("No items ordered yet.");
        } else {
            for (int i = 0; i < orderedItems.size(); i++) {
                System.out.println((i + 1) + ". " + orderedItems.get(i) + " - Quantity: " + orderedQuantities.get(i));
            }
        }

        System.out.println(); // Blank line for readability
    }
}
