import static java.lang.System.out;

import java.text.Format;
import java.util.ArrayList;
import java.util.Scanner;

public class EnhancedGrocery {

    // This class contains a product that is available in store
    public static class Product {

        public Product() {
        }

        public Product(String name, double price, String type) {
            productName = name;
            productPrice = price;
            productType = type;
        }

        String productName;
        double productPrice;
        String productType;
    }

    // This class contains a product taken by customers
    public static class Item {

        public Item(Product product, int quantity) {
            itemQuantity = quantity;
            itemTotal = product.productPrice * quantity;
        }

        String itemName;
        int itemQuantity;
        double itemPrice, itemTotal;

        public Item(Product product) {
        }

        public void print() {

        }

    }

    // This class contains a customer's info, receipt, and cart
    public static class Customer {

        String customerName;
        double customerTotal, customerPay, customerChange;
        Cart customerCart;

        public Customer(String name) {
            customerName = name;
            customerCart = new Cart();
        }

        public void addToCart(Product product, int quantity) {
            customerCart.addItem(new Item(product, quantity));
        }

        public void removeFromCart(Product product) {
            customerCart.removeItem(new Item(product));
        }
    }

    // This class contains the items and the total of the items
    public static class Cart {

        ArrayList<Item> items;
        public double total;
        public int totalItems;

        public Cart() {
            items = new ArrayList<>();
            total = 0;
            totalItems = 0;
        }

        public void addItem(Item item) {
            // Check if item already in cart
            for (int x = 0; x < items.size(); x++) {
                if (items.get(x).itemName.equals(item.itemName)) {
                    items.get(x).itemQuantity += item.itemQuantity;
                    total += item.itemTotal;
                    return;
                }
            }
            totalItems++;
            total += item.itemTotal;
            items.add(item);

        }

        public void removeItem(Item item) {
            for (int i = 0; i < items.size(); i++) {
                // Find similar item
                if (items.get(i).itemName.equals(item.itemName)) {
                    if (item.itemQuantity < items.get(i).itemQuantity) {
                        items.get(i).itemQuantity -= item.itemQuantity;
                        total -= item.itemTotal;
                    } else {
                        items.remove(i);
                    }
                }
            }
        }

        public void listItems() {
            for (int x = 0; x < items.size(); x++) {
                Item item = items.get(x);
                out.printf("%-15s ₱%-5.2f x%2d = %-5.2f %n", item.itemName, item.itemPrice, item.itemQuantity,
                        item.itemTotal);
            }
            out.println(total);
        }
    }

    public static void printProductList(ArrayList<Product> products) {
        String category = "";
        int lastCategory = -1;
        int categoryIndex = 0;
        for (int x = 0; x < products.size(); x++) {
            Product p = products.get(x);
            if (!category.equals(p.productType)) {
                category = p.productType;
                out.println(category);
                lastCategory = x - 1;
                categoryIndex++;
            }
            out.printf("    %d.%d. %-15s ₱%-5.2f%n", categoryIndex, x - lastCategory, p.productName,
                    p.productPrice);
        }
    }

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("C2Tea", 16, "Drink"));
        products.add(new Product("Cocacola 200ml", 11, "Drink"));
        products.add(new Product("Cocacola 325ml", 16, "Drink"));
        products.add(new Product("Skyflakes", 8, "Snacks"));
        products.add(new Product("Ice", 3, "Miscellaneous"));

        ArrayList<Customer> sales = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        do {
            // Name input check
            String name;
            while (true) {
                out.println("Enter Customer Name");
                name = input.nextLine();
                boolean hasDigit = false;
                for (char c : name.toCharArray()) {
                    if (Character.isDigit(c)) {
                        hasDigit = true;
                    }
                }
                if (hasDigit) {
                    out.println("A name must not have a number");
                    continue;
                }
                input.nextLine();
                break;
            }
            Customer customer = new Customer(name);
            do {// Shopping loop
                printProductList(products);

            } while (true);
        } while (true);
    }
}
