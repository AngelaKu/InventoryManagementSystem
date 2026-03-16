import java.util.Scanner;

public class InventoryApp {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();   // our manager object
        Scanner sc = new Scanner(System.in);     // for user input

        int choice = 0;

        while (choice != 7) {   // 7 = Exit
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Add product");
            System.out.println("2. View all products");
            System.out.println("3. Update product quantity");
            System.out.println("4. Remove product");
            System.out.println("5. Search product by ID");
            System.out.println("6. Save inventory to file");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            // read menu choice
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // clear invalid input
                continue;  // restart loop
            }

            switch (choice) {
                case 1:
                    // Add product
                    System.out.print("Enter product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume leftover newline

                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();

                    Product p = new Product(id, name, qty, price);
                    boolean added = inventory.addProduct(p);
                    if (added) {
                        System.out.println("Product added successfully.");
                    }
                    break;

                case 2:
                    // View all
                    inventory.displayAll();
                    break;

                case 3:
                    // Update quantity
                    System.out.print("Enter product ID to update: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter new quantity: ");
                    int newQty = sc.nextInt();

                    boolean updated = inventory.updateQuantity(updateId, newQty);
                    if (updated) {
                        System.out.println("Quantity updated.");
                    } else {
                        System.out.println("Product not found or invalid quantity.");
                    }
                    break;

                case 4:
                    // Remove product
                    System.out.print("Enter product ID to remove: ");
                    int removeId = sc.nextInt();

                    boolean removed = inventory.removeProduct(removeId);
                    if (removed) {
                        System.out.println("Product removed.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 5:
                    // Search by ID
                    System.out.print("Enter product ID to search: ");
                    int searchId = sc.nextInt();

                    Product found = inventory.findById(searchId);
                    if (found != null) {
                        System.out.println("Product found: " + found);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 6:
                    // Save to file
                    System.out.print("Enter filename to save (e.g., inventory.csv): ");
                    sc.nextLine(); // consume leftover newline
                    String filename = sc.nextLine();

                    inventory.saveToFile(filename);
                    break;

                case 7:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1–7.");
            }
        }

        sc.close();
    }
}