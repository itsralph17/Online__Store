package com.yearup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Load inventory from CSV file
        Inventory inventory = new Inventory();
        inventory.load("inventory.csv");

        // Initialize shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Display home screen
        while (true) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Display products
                    System.out.println("\nProducts:");
                    inventory.display();
                    System.out.print("Enter product ID to add to cart (or X to go back): ");
                    String productId = scanner.nextLine().trim().toUpperCase();

                    if (productId.equals("X")) {
                        break;
                    }

                    Product product = inventory.getProduct(productId);
                    if (product == null) {
                        System.out.println("Invalid product ID!");
                        break;
                    }

                    cart.addLineItem(new LineItem(product, 1));
                    System.out.println(product.getName() + " added to cart.\n");
                    break;

                case 2:
                    // Display cart
                    System.out.println("\nCart:");
                    cart.display();
                    System.out.println("Total: $" + cart.getTotal());
                    System.out.print("Enter C to Checkout or X to go back: ");
                    String cartChoice = scanner.nextLine().trim().toUpperCase();

                    if (cartChoice.equals("C")) {
                        // Checkout
                        System.out.println("\nCheckout:");
                        System.out.println("Total: $" + cart.getTotal());
                        System.out.print("Enter payment amount: ");
                        double payment = scanner.nextDouble();

                        if (payment < cart.getTotal()) {
                            System.out.println("Payment amount is less than total!");
                            break;
                        }

                        System.out.println("Change: $" + (payment - cart.getTotal()));
                        System.out.println("Items sold:");
                        cart.display();
                        cart.clear();
                    }

                    break;

                case 3:
                    // Exit application
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static class Inventory {
        private List<Product> products = new ArrayList<>();

        public void load(String fileName) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|"); // use pipe character as delimiter
                    if (data.length == 3) {
                        String id = data[0].trim().toUpperCase();
                        String name = data[1].trim();
                        double price = Double.parseDouble(data[2].trim());
                        products.add(new Product(id, name, price, 0)); // set initial quantity to 0
                    } else {
                        System.out.println("Invalid data: " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading inventory from file: " + e.getMessage());
            }
        }



        public void display() {
            for (Product product : products) {
                System.out.println(product.getId() + "\t" + product.getName() + "\t$" + product.getPrice());
            }
        }

        public Product getProduct(String productId) {
            for (Product product : products) {
                if (product.getId().equals(productId)) {
                    return product;
                }
            }
            return null;
        }
    }

}


/*        public void load(String filename) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    Product product = new Product(id, name, price, quantity);
                    products.add(product);
                }
            } catch (IOException e) {
                System.out.println("Failed to load inventory from file!");
            }
        }

 */
