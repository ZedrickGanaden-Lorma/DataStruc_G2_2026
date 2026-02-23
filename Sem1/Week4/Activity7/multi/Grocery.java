import java.util.Scanner;
import java.util.ArrayList;

public class Grocery {
    public static Scanner input = new Scanner(System.in);

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
            String name = new InputHandler("Enter customer name : ")
                    .min(3)
                    .onlyAlphabetic()
                    .nextString();
            this.name = name;
        }

        public boolean checkOut() {
            double pay = 0;
            while (pay < cart.getTotal()) {
                pay = new InputHandler("Enter Payment : ")
                        .nextDouble();
                if (pay < cart.getTotal()) {
                    System.err.println("You are short Php " + (cart.getTotal() - pay));
                    int choice = new InputHandler("""
                            [0] Reenter payment
                            [1] Remove items
                            [2] Discard cart

                            Choice : """)
                            .min(0)
                            .max(2)
                            .nextInt();
                    switch (choice) {
                        case 0:
                            continue;
                        case 1:
                            cart.removeItem();
                            break;
                        case 2:
                            System.out.println("Unsuccesful order");
                            return false;
                    }
                }
            }
            if (pay >= cart.getTotal()) {
                System.out.println("Successful order");
                System.out.println("Change : " + (pay - cart.getTotal()));
                System.out.println();
            }
            return true;
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
                String itemString = "Select item to remove from cart\n";
                for (int x = 0; x < items.size(); x++) {
                    Item i = items.get(x);
                    itemString += String.format("[%1d] %-15s Php %5.2f %5d%n", x, i.name, i.price, i.quantity);
                }
                itemString += "\n";
                // choice check
                int choice = new InputHandler(itemString + "Choice : ")
                        .min(0)
                        .max(items.size() - 1)
                        .nextInt();
                // quantity check
                int quantity = new InputHandler("Quantity : ")
                        .min(1)
                        .nextInt();
                setItem(items.get(choice).getProduct(), items.get(choice).quantity - quantity);
                if (items.size() == 1 && items.get(choice).quantity <= 1) {
                    break;
                }
                int stay = new InputHandler("""
                        Continue removing items?

                        [1] yes
                        [0] no

                        choice : """)
                        .setAllowedChars("01".toCharArray())
                        .nextInt();
                switch (stay) {
                    case 1:
                        continue;
                    case 0:
                        break;
                }
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
        }

        public double getTotal() {
            double total = 0;
            for (Item i : items) {
                total += i.price * i.quantity;
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
            System.out.println("Products in store: ");
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
                int choice = new InputHandler("Choice : ")
                        .min(0)
                        .max(products.size() - 1)
                        .nextInt();
                int quantity = new InputHandler("Enter quantity : ")
                        .nextInt();

                cart.setItem(products.get(choice), quantity);
                System.out.println();
                break;
            }
        }

        public boolean continueShopping() {
            int in = new InputHandler("""
                    Shop another product?

                    [1] yes
                    [0] no

                    choice : """)
                    .setAllowedChars("01".toCharArray())
                    .nextInt();
            switch (in) {
                case 0:
                    return false;
                case 1:
                    return true;
            }
            return false;
        }
    }

    public static boolean continueServingNewCustomers() {
        char choice = new InputHandler("""
                Wait and serve a new customer?

                [1] yes
                [0] no

                Choice : """)
                .setAllowedChars("01".toCharArray())
                .nextChar();
        switch (choice) {
            case '1':
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        InputHandler.setGlobalNoPauseOnError();
        System.out.println("Welcome to EFM Grocery Shopping");
        Storage store = new Storage(
                new Product("Skyflakes", 8d),
                new Product("Buttercookies", 9d),
                new Product("Coca Cola 250ml", 11d),
                new Product("Mountain Dew", 12d));
        boolean continueCheckout = true;

        // Customer loop
        while (continueCheckout) {
            ArrayList<Customer> successfulOrders = new ArrayList<>();
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
            if (customer.checkOut()) {
                // successful
                successfulOrders.add(customer);
            }
            continueCheckout = continueServingNewCustomers();
        }
        System.out.println("Thank you for shopping with us");

    }
}
