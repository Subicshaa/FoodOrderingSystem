import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodOrderingSystem {

    enum MenuItem {
        PIZZA(1, "Pizza", 10),
        BURGER(2, "Burger", 8),
        SANDWICH(3, "Sandwich", 6),
        SALAD(4, "Salad", 5);

        private final int number;
        private final String name;
        private final double price;

        MenuItem(int number, String name, double price) {
            this.number = number;
            this.name = name;
            this.price = price;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public static MenuItem fromNumber(int number) {
            for (MenuItem item : MenuItem.values()) {
                if (item.getNumber() == number) {
                    return item;
                }
            }
            return null;
        }
    }

    static class OrderItem {
        private MenuItem menuItem;
        private int quantity;

        public OrderItem(MenuItem menuItem, int quantity) {
            this.menuItem = menuItem;
            this.quantity = quantity;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotalPrice() {
            return menuItem.getPrice() * quantity;
        }
    }

    static List<OrderItem> orderList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Food Ordering System");
        System.out.println("---------------------");

        while (true) {
            System.out.println("1. View Menu");
            System.out.println("2. Order Food");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
                continue;
            }
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
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            displayOrderSummary();
        }
    }

    private static void viewMenu() {
        System.out.println("Menu");
        System.out.println("----");
        for (MenuItem item : MenuItem.values()) {
            System.out.println(item.getNumber() + ". " + item.getName() + " ($" + item.getPrice() + ")");
        }
    }

    private static void orderFood(Scanner scanner) {
        System.out.print("Enter food number: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }
        int foodNumber = scanner.nextInt();

        MenuItem menuItem = MenuItem.fromNumber(foodNumber);
        if (menuItem == null) {
            System.out.println("Invalid food number.");
            return;
        }

        System.out.print("Enter quantity: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
            return;
        }
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        orderList.add(new OrderItem(menuItem, quantity));

        System.out.println(menuItem.getName() + " ordered! Quantity: " + quantity);
        System.out.println("Total: $" + (menuItem.getPrice() * quantity));
    }

    private static void displayOrderSummary() {
        System.out.println("Order Summary");
        System.out.println("--------------");

        if (orderList.isEmpty()) {
            System.out.println("No items ordered yet.");
        } else {
            int index = 1;
            double grandTotal = 0;
            for (OrderItem orderItem : orderList) {
                System.out.println(index + ". " + orderItem.getMenuItem().getName() + " - Quantity: " + orderItem.getQuantity() + " - Subtotal: $" + orderItem.getTotalPrice());
                grandTotal += orderItem.getTotalPrice();
                index++;
            }
            System.out.println("Grand Total: $" + grandTotal);
        }

        System.out.println(); 
    }
}
