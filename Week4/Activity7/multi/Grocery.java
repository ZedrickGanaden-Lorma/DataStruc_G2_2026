import java.util.Scanner;
import java.util.ArrayList;

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

        public Product getProduct() {
            return new Product(this.name, this.price);
        }
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
        Cart cart = new Cart();

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

        public boolean checkOut() {
            boolean checkingOut = true;
            boolean resolved = false;
            double pay = 0;
            while (checkingOut) {
                System.out.print("Enter Payment : ");
                if (!input.hasNextDouble()) {
                    System.err.println("Can only accept numbers as input");
                    pause();
                    continue;
                }
                pay = input.nextDouble();
                input.nextLine();
                int choice;
                while (pay < cart.getTotal()) {
                    if (checkingOut == false) {
                        break;
                    }
                    System.err.println("You are short Php " + (cart.getTotal() - pay));
                    System.out.print("""
                            [0] Reenter payment
                            [1] Remove items
                            [2] Discard cart
                            """);
                    if (!input.hasNextInt()) {
                        System.err.println("Can only input numbers presented");
                        pause();
                        continue;
                    }
                    choice = input.nextInt();
                    input.nextLine();
                    if (!(choice >= 0 && choice <= 3)) {
                        System.err.println("Enter only the choices presented");
                        pause();
                        continue;
                    }
                    boolean reEnter = false;
                    switch (choice) {
                        case 0:
                            reEnter = true;
                            break;
                        case 1:
                            cart.removeItem();
                            break;
                        case 2:
                            checkingOut = false;
                            break;
                    }
                    if (reEnter)
                        break;
                }
                if (pay >= cart.getTotal()) {
                    break;
                }
            }
            if (pay >= cart.getTotal()) {
                resolved = true;
            }
            return resolved;
        }
    }

    public static class Cart {
        ArrayList<Item> items = new ArrayList<>();

        public void setItem(Product product, int quantity) {
            boolean match = false;
            for (int x = 0; x < items.size(); x++) {
                if (product.name.equals(items.get(x).name)) {
                    if (quantity <= 0) {
                        System.out.println("Removing item");
                        items.remove(x);
                        break;
                    }
                    System.out.println("Overwriting item quantity");
                    items.get(x).quantity = quantity;
                    match = true;
                    return;
                }
            }
            if (!match && quantity > 0) {
                items.add(new Item(product, quantity));
            }
        }

        public void removeItem() {
            while (true) {
                System.out.println("Select item to remove from cart");
                for (int x = 0; x < items.size(); x++) {
                    Item i = items.get(x);
                    System.out.printf("[%1d] %-15s Php %5.2f %5d%n", x, i.name, i.price, i.quantity);
                }
                System.out.println();
                // choice check
                int choice;
                while (true) {
                    System.out.println("Choice : ");
                    if (!input.hasNextInt()) {
                        System.err.println("Can only enter numbers presented");
                        pause();
                        continue;
                    }
                    choice = input.nextInt();
                    input.nextLine();
                    if (!(choice >= 0 && choice < items.size())) {
                        System.err.println("Can only enter numbers presented");
                        pause();
                        continue;
                    }
                    break;
                }

                input.nextLine();
                // quantity check
                int quantity = 0;
                while (true) {
                    // numbers only
                    while (true) {
                        if (!input.hasNextInt()) {
                            System.err.println("Can only enter numbers");
                            pause();
                            continue;
                        }
                        break;
                    }
                    // lower than current only
                    quantity = input.nextInt();
                    input.nextLine();
                    if (quantity >= items.get(choice).quantity) {
                        System.err.println("Can only enter counts lower than current item quantity");
                        pause();
                        continue;
                    }
                    break;
                }
                System.out.println();
                setItem(items.get(choice).getProduct(), quantity);
            }
        }

        public void displayItems() {
            double total = 0;
            System.out.println("Cart items");
            System.out.printf("%-15s %-7s %-7s %-10s%n", "Item name", "price", "quantity", "Item total");
            for (Item i : items) {
                double itemTotal = i.price * i.quantity;
                total += itemTotal;
                System.out.printf("%-15s %-6.2f  %-8d %-10s%n", i.name, i.price, i.quantity, itemTotal);
            }
            System.out.println("\nCart total : " + String.format("%-5.2f", total));
            System.out.println();
            pause();
        }

        public double getTotal() {
            double total = 0;
            for (Item i : items) {
                total = i.price * i.quantity;
            }
            return total;
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
            if (cart.items.size() > 0) {
                cart.displayItems();
            }
            // Selection
            while (true) {
                displayProducts();
                System.out.print("Choice : ");
                String in = input.nextLine();

                boolean hasInvalidChar = false;
                for (char c : in.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        System.err.println("Cannot have any letters in input");
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

                cart.setItem(products.get(choice), quantity);
                System.out.println();
                break;
            }
        }

        public boolean continueShopping() {
            boolean result = false;
            while (true) {
                System.out.print("""
                        Shop another product?
                        [0] no
                        [1] yes

                        choice :  """);
                if (!input.hasNextInt()) {
                    System.err.println("Can only input choices presented");
                    pause();
                    continue;
                }
                int in = input.nextInt();
                input.nextLine();
                switch (in) {
                    case 0:
                        return false;
                    case 1:
                        return true;
                }
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
                // looping shop for items
                store.shopProducts(customer.cart, store);
                // continue prompt
                continueShopping = store.continueShopping();
            }
            customer.cart.displayItems();
            // Checkout
            customer.checkOut();
        }

    }
}
