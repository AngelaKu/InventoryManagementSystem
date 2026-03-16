import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public boolean addProduct(Product p) {
        if (findById(p.getId()) != null) {
            System.out.println("Product with this ID already exists.");
            return false;
        }
        products.add(p);
        return true;
    }

    public boolean removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateQuantity(int id, int newQty) {
        Product p = findById(id);
        if (p != null && newQty >= 0) {
            p.setQuantity(newQty);
            return true;
        }
        return false;
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void displayAll() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {

            for (Product p : products) {
                writer.write(
                    p.getId() + "," +
                    p.getName() + "," +
                    p.getQuantity() + "," +
                    p.getPrice() + "\n"
                );
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving file.");
            e.printStackTrace();
        }
    }
}