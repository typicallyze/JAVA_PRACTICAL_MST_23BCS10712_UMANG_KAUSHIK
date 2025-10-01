import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductManager {

    static class PEx extends Exception {
        public PEx(String msg) {
            super(msg);
        }
    }

    Map<String, Double> p = new HashMap<>();

    public void applyDiscount(String id, double d) throws PEx {
        if (d < 0 || d > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100!");
        }
        if (!p.containsKey(id)) {
            throw new PEx("Product ID not found!");
        }
        
        double newPrice = p.get(id) * (1 - d / 100);
        p.put(id, newPrice);
        System.out.println("New price for " + id + ": $" + String.format("%.1f", newPrice));
    }

    public static void main(String[] args) {
        ProductManager app = new ProductManager();

        app.p.put("P001", 50.0);
        app.p.put("P002", 100.0);
        System.out.println("Adding products: P001=$50.0, P002=$100.0\n");


        try {
            System.out.println("Applying 20% discount to P001...");
            app.applyDiscount("P001", 20);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\nApplying 150% discount to P002...");
            app.applyDiscount("P002", 150);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\nApplying discount to P999...");
            app.applyDiscount("P999", 10);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n--- Enter custom values ---");
        System.out.print("Enter product ID: ");
        String inputId = sc.next();
        System.out.print("Enter discount %: ");
        double inputDiscount = sc.nextDouble();
        
        try {
            System.out.println("\nApplying " + (int)inputDiscount + "% discount to " + inputId + "...");
            app.applyDiscount(inputId, inputDiscount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
