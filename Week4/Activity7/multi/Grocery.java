import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Grocery {
    public static Scanner input = new Scanner(System.in);

    static void pause() {
        System.out.println("Press enter to continue");
        input.nextLine();
        System.out.println();
    }

    public static class Item {
        public Item(Product product, int quantity) {
            this.name = product.name;
            this.quantity = quantity;
            this.price = product.price;
        }

        String name;
        int quantity;
        double price;
    }

    public static class Product {
        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        String name;
        double price;
    }

    public static class Customer {
        String name;
        double total, pay, change;
        Cart cart;

        public Customer(String name) {
            this.name = name;
        }

        // input method for customer
        public Customer() {
            enterName();
        }

        public void enterName() {
            while (true) {
                System.out.println("Enter customer name : ");
                String in = input.nextLine();
                // Number check
                boolean hasDigit = false;
                if (in.length() < 3) {
                    System.err.println("Cannot be less than 3 characters long");
                    pause();
                    continue;
                }
                for (char c : in.toCharArray()) {
                    if (Character.isDigit(c)) {
                        System.err.println("Cannot have a number in name");
                        pause();
                        break;
                    }
                    if (!Character.isAlphabetic(c)) {
                        System.err.println("Can only have alphabetic characters in name");
                        pause();
                        break;
                    }
                }
                if (hasDigit)
                    continue;
                this.name = in;
                break;
            }
        }

        public void checkOut() {

        }
    }

    public static class Cart {
        ArrayList<Item> items;

        public Cart() {
            items = new ArrayList<>();
        }

        public void addItem(Product product, int quantity) {
            items.add(new Item(product, quantity));
        }
    }

    public static class Storage {
        private ArrayList<Product> products = new ArrayList<>();

        public Storage(Product... products) {
            for (Product p : products) {
                this.products.add(p);
            }
        }

        private void displayProducts() {
            System.out.println("Products in store:");
            for (int x = 0; x < products.size(); x++) {
                Product p = products.get(x);
                System.out.printf("[%1d] %-15s Php %5.2f%n", x, p.name, p.price);
            }
            System.out.println();
        }

        public void shopProducts(Cart cart, Storage store) {
            // Selection
            while (true) {
                displayProducts();
                System.out.println("[x] finish");
                System.out.print("Choice : ");
                String in = input.nextLine();
                boolean hasInvalidChar = false;
                for (char c : in.toCharArray()) {
                    if (!Character.isDigit(c) && c != 'x') {
                        System.err.println("Cannot have any letters except x in input");
                        pause();
                        hasInvalidChar = true;
                        break;
                    }
                }
                if (hasInvalidChar)
                    continue;
                int choice = Integer.parseInt(in);

                // Quantity
                int quantity = 0;
                while (true) {
                    System.out.println("Enter quantity");
                    if (!input.hasNextInt()) {
                        System.err.println("Cannot have anything else than numbers as input");
                        pause();
                        continue;
                    }
                    quantity = input.nextInt();
                    input.nextLine();
                    break;
                }

                cart.addItem(products.get(choice), quantity);
                System.out.println();
                break;
            }
        }

        public boolean continueShopping() {
            boolean result = false;
            while (true) {
                System.out.println("""
                        Shop another product?
                        [0] no
                        [1] yes

                        choice : """);
                String in = input.nextLine();
                if (in.length() > 1) {
                    System.err.println("Cannot have more than 1 character in choice");
                    pause();
                    continue;
                }
                switch (in) {
                    case "0":
                        result = false;
                    case "1":
                        result = true;
                }
                return result;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to EFM Grocery Shopping");
        Storage store = new Storage(
                new Product("Skyflakes", 8d),
                new Product("Buttercookies", 9d),
                new Product("Coca Cola 250ml", 11d),
                new Product("Mountain Dew", 12d));
        boolean continueCheckout = true;
        // Customer loop
        while (continueCheckout) {
            Customer customer = new Customer();
            boolean continueShopping = true;

            // Shopping loop
            while (continueShopping) {
                store.shopProducts(customer.cart, store);
                // continue prompt
                continueShopping = store.continueShopping();
            }
            // Checkout
            customer.checkOut();
        }

    }
}
